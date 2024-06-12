package com.example.gnb.user.controller;

import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
@Slf4j
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public User join(@RequestBody UserJoinRequest user){

        User userEntity = User.builder()
                .userName(user.getUserName())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .userTel(user.getUserTel())
                .role("ROLE_USER")
                .build();

        if (userRepository.findByUserName(user.getUserName()) == null) {
            log.warn(userEntity.toString());
            return userRepository.save(userEntity);
        }

        return null;
    }


}
