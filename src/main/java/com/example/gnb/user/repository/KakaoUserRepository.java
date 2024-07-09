package com.example.gnb.user.repository;

import com.example.gnb.user.entity.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
    KakaoUser findByEmail(String email);
}
