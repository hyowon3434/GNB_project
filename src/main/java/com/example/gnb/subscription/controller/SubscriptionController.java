package com.example.gnb.subscription.controller;

import com.example.gnb.subscription.dto.updatePaymentMgrDTO;
import com.example.gnb.subscription.service.SubscriptionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/payment")
public class SubscriptionController {

    @Value("${toss.payment.secret-key}")
    private String secretKey;

    @Value("${toss.payment.toss-api-url}")
    private String tossApiUrl;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/success")
    public ResponseEntity<?> newPayment(@RequestParam Map<String, String> param) throws IOException, InterruptedException {

        String encodedAuthHeader = Base64.getEncoder().encodeToString(secretKey.getBytes());
        Map<String, String> body = new HashMap<>();
        body.put("customerKey", param.get("customerKey"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tossApiUrl + "/" + param.get("authKey")))
                .header("Authorization", "Basic " + encodedAuthHeader)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

//        if (response.statusCode() == 200){
//            JsonNode responseBody = objectMapper.readTree(response.body());
//            log.warn(responseBody.toPrettyString());
//            return responseBody.toPrettyString();
//        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> putPaymentMgr(updatePaymentMgrDTO request){
        return ResponseEntity.ok(subscriptionService.updatePaymentMgr(request));
    }
}
