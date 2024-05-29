package com.example.gnb.oauth2.kakao.client;

import java.net.URI;

import com.example.gnb.oauth2.kakao.config.KakaoFeignConfiguration;
import com.example.gnb.oauth2.kakao.dto.KakaoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "kakaoClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoClient {

    // 토큰을 발급 받은 후 access 토큰 값으로 baseUrl (https://kapi.kakao.com/v2/user/me)에 사용자 정보를 요
    //
    @PostMapping
    KakaoToken getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    // baseUrl(https://kauth.kakao.com/oauth/token)로 클라이언트 아이디(RestApiKey),
    // 리다이렉트 url, 인증 code 값, grant_type(authorization_code 고정) 값으로 http body에 묶어서 요청 후
    // 카카오로부터 토큰 발급
    @PostMapping
    KakaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);

}
