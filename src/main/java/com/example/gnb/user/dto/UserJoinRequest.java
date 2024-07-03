package com.example.gnb.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserJoinRequest {
    private String email;
    private String userName;
    private String password;
    private String userTel;
    private String userPlan = "STARTER";
    private String role = "ROLE_USER";
    private String planBeginAt = LocalDate.now().toString();
    private String planFinishAt = "9999-12-31";
}
