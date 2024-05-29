package com.example.gnb.oauth2.kakao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoToken {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private Long refreshTokenExpiresIn;

    public static KakaoToken fail(){
        return new KakaoToken();
    }

    public KakaoToken(final String accessToken, final String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }



}
