package com.example.gnb.oauth2.kakao.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
public class KakaoAccount {
    private Profile profile;
    private String gender;
    private String birthday;
    private String email;
}
