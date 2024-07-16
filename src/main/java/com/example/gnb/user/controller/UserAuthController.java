package com.example.gnb.user.controller;

import com.example.gnb.config.auth.PrincipalDetailsService;
import com.example.gnb.config.jwt.JwtTokenProvider;
import com.example.gnb.config.oauth.PrincipalOauth2UserService;
import com.example.gnb.user.dto.UserLoginRequest;
import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.dto.JwtAuthenticationResponse;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final UserService userService;
    private final PrincipalDetailsService service;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) throws Exception{

        String jwt = "";
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            log.warn("authentication : [" + authentication.getPrincipal().toString() + "]");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwt = tokenProvider.generateToken(authentication);
            log.warn("토큰 생성 완료 [" + jwt + "]");
        }catch (Exception e){
            log.error("Authentication failed", e);
            throw new Exception("not authenticated.......");
        }


        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/user/api/join")
    public ResponseEntity<?> registerUser(@RequestBody UserJoinRequest joinRequest) {
        User user = userService.registerUser(joinRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/callback")
    public ResponseEntity<?> successKakao(@RequestParam("code") String code){
        OAuth2AuthenticationToken token = principalOauth2UserService.getKakaoUser(code);
        
        return ResponseEntity.ok(token);
    }
}