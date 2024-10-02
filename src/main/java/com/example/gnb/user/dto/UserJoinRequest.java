package com.example.gnb.user.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserJoinRequest {
    private String email;
    private String userName;
    private String password;
    private String userTel;
    private String userPlan = "STARTER";
    private String role = "ROLE_USER";
    private LocalDateTime planBeginAt = LocalDateTime.now();
    private LocalDateTime planFinishAt = planBeginAt.plusYears(1000);
}
