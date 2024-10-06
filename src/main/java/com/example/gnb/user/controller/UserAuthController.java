package com.example.gnb.user.controller;

import com.example.gnb.config.auth.PrincipalDetailsService;
import com.example.gnb.config.jwt.JwtTokenProvider;
import com.example.gnb.config.oauth.PrincipalOauth2UserService;
import com.example.gnb.user.dto.UserLoginRequest;
import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.dto.JwtAuthenticationResponse;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import com.example.gnb.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) throws Exception{
        log.warn(loginRequest.toString());
        String jwt = "";
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userRepository.findByEmail(loginRequest.getEmail());
            session.setAttribute("user", user);
            //log.warn("토큰 인증된 유저 이메일 : " + tokenProvider.getUserEmailFromJWT(jwt));
            jwt = tokenProvider.generateToken(authentication);
        }catch (Exception e){
            log.error("Authentication failed", e);
            throw new Exception("not authenticated.......");
        }


        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/join")
    public ResponseEntity<?> registerUser(@RequestBody UserJoinRequest joinRequest,
                                          @RequestHeader("Authorization") String token) throws Exception{
        if (token != null) {
            throw new Exception("이미 로그인된 사용자입니다.");
        }
        User user = userService.registerUser(joinRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/callback")
    public ResponseEntity<?> successKakao(@RequestParam("code") String code){
        OAuth2AuthenticationToken token = principalOauth2UserService.getKakaoUser(code);
        
        return ResponseEntity.ok(token);
    }
}