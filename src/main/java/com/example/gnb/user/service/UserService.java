package com.example.gnb.user.service;

import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserJoinRequest joinRequest) {
        if (userRepository.findByEmail(joinRequest.getEmail()) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(joinRequest.getEmail())
                .userName(joinRequest.getUserName())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .userTel(joinRequest.getUserTel())
                .userPlan(joinRequest.getUserPlan())
                .role(joinRequest.getRole())
                .planBeginAt(joinRequest.getPlanBeginAt())
                .planFinishAt(joinRequest.getPlanFinishAt())
                .autoRenewal(true)
                .build();

        return userRepository.save(user);
    }

}
