package com.picpay.picpay_challenge.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://util.devi.tools/api/v1/notify";

    public void notifyUser() {
        try {
            restTemplate.postForEntity(URL, null, String.class);
        } catch (Exception e) {
            System.out.println("⚠️ Falha ao enviar notificação: " + e.getMessage());
        }
    }
}