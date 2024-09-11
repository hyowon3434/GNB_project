package com.example.gnb.config;

import com.example.gnb.config.jwt.JwtAuthenticationFilter;
import com.example.gnb.config.jwt.JwtTokenProvider;
import com.example.gnb.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .requestMatchers("/user/login", "/user/api/join", "/loginForm", "/joinForm", "/oauth2/**").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                        .oauth2Login()
                                .loginPage("/loginFrom")
                                    .userInfoEndpoint()
                                        .userService(principalOauth2UserService)
                .and()
                        .defaultSuccessUrl("/product", true)
                .and()
                        .logout()
                                .logoutSuccessUrl("/product")
                                        .permitAll();

        http
                .securityContext()
                .securityContextRepository(new HttpSessionSecurityContextRepository());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() throws Exception{
        return new BCryptPasswordEncoder();
    }
}