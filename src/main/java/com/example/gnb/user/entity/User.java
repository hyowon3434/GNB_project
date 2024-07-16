package com.example.gnb.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String userName;
    @Setter
    @Column(nullable = false)
    private String password;
    @Setter
    @Column(nullable = false)
    private String userTel;
    @Setter
    @Column(nullable = false)
    private String userPlan;
    @Column(nullable = false)
    private String role;
    @Column
    private String provider;
    @Column
    private String providerId;
    @Setter
    @Column(nullable = false)
    private String planBeginAt;
    @Setter
    @Column(nullable = false)
    private String planFinishAt;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp userJoinDate; // 가입날짜
    @Setter
    @Column(nullable = false)
    private Boolean autoRenewal;

    @Builder
    public User( Long userId,String email, String userName, String password, String userTel,
                 String userPlan, String role, String provider, String providerId,
                 String planBeginAt, String planFinishAt, Timestamp userJoinDate, Boolean autoRenewal){
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userTel = userTel;
        this.userPlan = userPlan;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.planBeginAt = planBeginAt;
        this.planFinishAt = planFinishAt;
        this.userJoinDate = userJoinDate;
        this.autoRenewal = autoRenewal;

    }
}
