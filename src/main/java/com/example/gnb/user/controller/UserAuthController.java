package com.example.gnb.user.controller;

import com.example.gnb.config.auth.PrincipalDetailsService;
import com.example.gnb.config.jwt.JwtTokenProvider;
import com.example.gnb.user.dto.UserLoginRequest;
import com.example.gnb.user.dto.UserJoinRequest;
import com.example.gnb.user.dto.JwtAuthenticationResponse;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final PrincipalDetailsService service;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) throws Exception{
        log.warn("are u here??? : " + loginRequest.getEmail());
        String dbPw = service.loadUserByUsername(loginRequest.getEmail()).getPassword().toString();
        String rawPw = loginRequest.getPassword();
        log.warn("로그인 pw : " + rawPw+ "     "+ dbPw);
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
}