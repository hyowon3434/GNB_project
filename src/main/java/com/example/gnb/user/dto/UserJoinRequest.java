package com.example.gnb.user.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private int userTel;
    private String userPlan = "BASIC_PLAN";
    private String role;
    private String planBeginAt = LocalDate.now().toString();
    private String planFinishAt = "9999-12-31";
}
