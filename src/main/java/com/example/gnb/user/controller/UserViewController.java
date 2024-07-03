package com.example.gnb.user.controller;

import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class UserViewController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm(){
        log.warn("loginForm 들어옴!!!!!!!!!" + LocalDateTime.now());
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }
//
//    @PostMapping("/user/api/join")
//    public String join(UserJoinRequest user) throws Exception{
//        log.warn("[USERJOINREQUEST] " + user.toString());
//        User userEntity = User.builder()
//                .email(user.getEmail())
//                .userName(user.getUserName())
//                .password(bCryptPasswordEncoder.encode(user.getPassword()))
//                .userTel(user.getUserTel())
//                .role("ROLE_USER")
//                .userPlan(user.getUserPlan())
//                .planBeginAt(user.getPlanBeginAt())
//                .planFinishAt(user.getPlanFinishAt())
//                .build();
//
//        if (userRepository.findByUserName(user.getUserName()) == null) {
//            log.warn(userEntity.toString());
//            userRepository.save(userEntity);
//            return "loginForm";
//        }
//
//        return null;
//    }

}
