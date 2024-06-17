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
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String userName; // email 아이디
    @Setter
    @Column(nullable = false)
    private String password;
    @Setter
    @Column(nullable = false)
    private int userTel;
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

    @Builder
    public User( Long userId, String userName, String password, int userTel,
                 String userPlan, String role, String provider, String providerId,
                 String planBeginAt, String planFinishAt, Timestamp userJoinDate){
        this.userId = userId;
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

    }
}
