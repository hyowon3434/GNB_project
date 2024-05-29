package com.example.gnb.oauth2.kakao.service;
import java.net.URI;

import com.example.gnb.oauth2.kakao.client.KakaoClient;
import com.example.gnb.oauth2.kakao.dto.KakaoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    @Autowired
    private final KakaoClient client;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    // 토큰 발급 후 카카오유저 정보 조회
    public KakaoToken getInfo(final String code) {
        final KakaoToken token = getToken(code);
        log.debug("token = {}", token);
        try {
            return client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("something error..on getInfo function", e);
            return KakaoToken.fail();
        }
    }


    // 토큰 발급
    private KakaoToken getToken(final String code) {
        try {
            return client.getToken(new URI(kakaoAuthUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error.. on getToken function", e);
            return KakaoToken.fail();
        }
    }
}
