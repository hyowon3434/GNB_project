package com.example.gnb.config.auth;

import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            log.warn("유저정보 : " + userEntity.toString());
            return new PrincipalDetails(userEntity);
        }

        throw new UsernameNotFoundException("User not found with email : " + email);
    }
}
