package com.picpay.picpay_challenge.client;

import com.picpay.picpay_challenge.exceptions.AuthorizationServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AuthorizerClient {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://util.devi.tools/api/v2/authorize";

    public boolean isAuthorized() {
        String cacheKey = "external:authorizer:status";
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();

        Boolean cached = (Boolean) ops.get(cacheKey);
        if (cached != null) {
            System.out.println("Autorização obtida do Redis (cache).");
            return cached;
        }

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(URL, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
                if (data != null && data.containsKey("authorization")) {
                    boolean authorized = (boolean) data.get("authorization");

                    ops.set(cacheKey, authorized, 1, TimeUnit.MINUTES);

                    System.out.println("Autorização consultada do serviço externo e salva no Redis.");
                    return authorized;
                }
            }
            throw new AuthorizationServiceException("Resposta inválida do serviço autorizador.");

        } catch (Exception e) {
            throw new AuthorizationServiceException("Erro ao consultar serviço autorizador: " + e.getMessage());
        }
    }
}
