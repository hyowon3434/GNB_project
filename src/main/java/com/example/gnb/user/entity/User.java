package com.example.gnb.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;
    @Setter
    @Column(nullable = false)
    private String userName; // email 아이디
    @Setter
    @Column(nullable = false)
    private int userTel;
    @Column(nullable = false)
    private String kakaoId;
    @Column(nullable = false)
    private String userImage;
    @Column(nullable = false)
    private String userPlan;
    @Setter
    @Column(nullable = false)
    private LocalDateTime planBeginAt;
    @Setter
    @Column(nullable = false)
    private LocalDateTime planFinishAt;
    @Column(nullable = false)
    private LocalDateTime userJoinDate; // 가입날짜
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime userLastLogin; // 마지막 로그인

    @Builder
    public User( Long userId, String userName, int userTel, String userPlan,
                 LocalDateTime planBeginAt, LocalDateTime planFinishAt, LocalDateTime userJoinDate,
                 LocalDateTime userLastLogin, String kakaoId, String userImage){
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.userPlan = userPlan;
        this.planBeginAt = planBeginAt;
        this.planFinishAt = planFinishAt;
        this.userJoinDate = userJoinDate;
        this.userLastLogin = userLastLogin;
        this.kakaoId = kakaoId;
        this.userImage = userImage;
    }
}
