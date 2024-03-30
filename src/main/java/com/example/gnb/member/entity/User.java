package com.example.gnb.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userIdNum;
    @Column(nullable = false)
    private String userId;
    @Setter
    @Column(nullable = false)
    private String userName;
    @Setter
    @Column(nullable = false)
    private int userTel;
    @Column(nullable = false)
    private String userPlan;
    @Setter
    @Column(nullable = false)
    private String planBeginAt;
    @Setter
    @Column(nullable = false)
    private String planFinishAt;

    @Builder
    public User(Long userIdNum, String userId, String userName, int userTel,
                String userPlan, String planBeginAt, String planFinishAt){
        this.userId = userId;
        this.userIdNum = userIdNum;
        this.userName = userName;
        this.userTel = userTel;
        this.userPlan = userPlan;
        this.planBeginAt = planBeginAt;
        this.planFinishAt = planFinishAt;
    }
}
