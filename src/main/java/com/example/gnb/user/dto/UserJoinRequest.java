package com.example.gnb.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Data
public class UserJoinRequest {
    private String userName;
    private String password;
    private int userTel;
    private String userPlan = "BASIC_PLAN";
    private String role = "ROLE_USER";
    private String planBeginAt = LocalDate.now().toString();
    private String planFinishAt = "9999-12-31";
}
