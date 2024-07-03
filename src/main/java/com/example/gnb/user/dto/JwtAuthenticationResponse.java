package com.example.gnb.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class JwtAuthenticationResponse {
    String jwt;

    public JwtAuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
