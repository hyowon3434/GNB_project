package com.example.gnb.user.controller;

import com.example.gnb.config.auth.PrincipalDetails;
import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@CrossOrigin(value = "*")
@Slf4j
@RequestMapping("/user/api")
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public String login(Authentication authentication,
                        @AuthenticationPrincipal PrincipalDetails userDetails){

        log.warn("/test/login=======================");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.warn("authentication : " + principalDetails.getUser());
        log.warn("userDetails : " + userDetails.getUser());

        return userDetails.getUser().toString();
    }




}
