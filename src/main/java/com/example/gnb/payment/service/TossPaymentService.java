package com.example.gnb.payment.service;

import com.example.gnb.subscription.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class TossPaymentService {

    private String tossSecretKey;

    private final String TOSS_API_URL = "https//api.tosspayments.com/v1";

    @Autowired
    private RestTemplate restTemplate;

    public TossPaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createBillingKey(String customerKey, String cardNumber,
                                   String cardExpirationYear, String cardExpirationMonth,
                                   String cardPassword, String customerBirthday){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(tossSecretKey, "");

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("cardNumber", cardNumber);
        payloadMap.put("cardExpirationYear", cardExpirationYear);
        payloadMap.put("cardExpirationMonth", cardExpirationMonth);
        payloadMap.put("cardPassword", cardPassword);
        payloadMap.put("customerBirthday",customerBirthday);
        payloadMap.put("customerKey", customerKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payloadMap, headers);

        Map<String, Object> response = restTemplate.postForObject(TOSS_API_URL + "/billing/authorizations/card", request, Map.class);

        return (String) response.get("billingKey");
    }

    public void processSubscriptionPayment(Subscription subscription, String billingKey){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(tossSecretKey, "");

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("amount", subscription.getPrice());
        payloadMap.put("orderId", "ORDER-" + subscription.getSubscriptionId() + "-" + System.currentTimeMillis());
        payloadMap.put("orderName", subscription.getPlanType() + "Subscription");
        payloadMap.put("customerKey", subscription.getUser().getUserId().toString());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payloadMap, headers);
        restTemplate.postForObject(TOSS_API_URL + "/billing/" + billingKey, request, Map.class);
    }
}
