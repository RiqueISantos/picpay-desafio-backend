package com.picpay.picpay_challenge.client;

import com.picpay.picpay_challenge.exceptions.AuthorizationServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthorizerClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://util.devi.tools/api/v2/authorize";

    public boolean isAuthorized() {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(URL, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
                if (data != null && data.containsKey("authorization")) {
                    return (boolean) data.get("authorization");
                }
            }
        } catch (Exception e) {
            throw new AuthorizationServiceException("Erro ao consultar serviço autorizador: " + e.getMessage());
        }
        return false;
    }

}
