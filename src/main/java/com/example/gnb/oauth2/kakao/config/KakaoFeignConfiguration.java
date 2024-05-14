package com.example.gnb.oauth2.kakao.config;

import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KakaoFeignConfiguration {

    @Bean
    public Client fiegnClient(){
        return new Client.Default(null, null);
    }
}
