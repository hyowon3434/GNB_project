package com.example.gnb.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "kakaoUser")
@Data
public class KakaoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long kakaoUserId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = true)
    private String userTel;
    @Column(nullable = false)
    private String userPlan;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String planBeginAt;
    @Column(nullable = false)
    private String planFinishAt;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp userJoinDate;

    @Builder
    public KakaoUser(Long kakaoUserId, String email, String nickname,
                     String userTel, String userPlan, String role,
                     String planBeginAt, String planFinishAt, Timestamp userJoinDate){
        this.kakaoUserId = kakaoUserId;
        this.nickname = nickname;
        this.email = email;
        this.userTel = userTel;
        this.userPlan = userPlan;
        this.role = role;
        this.planBeginAt = planBeginAt;
        this.planFinishAt = planFinishAt;
        this.userJoinDate = userJoinDate;
    }

}
