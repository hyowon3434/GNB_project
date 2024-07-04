package com.example.gnb.config.oauth;

import com.example.gnb.config.auth.PrincipalDetails;
import com.example.gnb.user.entity.KakaoUser;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.KakaoUserRepository;
import com.example.gnb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final KakaoUserRepository kakaoUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equals("kakao")) {
            return joinKakaoUser(oAuth2User);
        }

        return oAuth2User;
    }

    private OAuth2User joinKakaoUser(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        KakaoUser kakaoUser = kakaoUserRepository.findByEmail(email);

        if (kakaoUser != null) {
            log.error("이미 존재하는 회원입니다.");
        }

        KakaoUser newMember = KakaoUser.builder()
                .email(kakaoAccount.get("email").toString())
                .nickname(kakaoAccount.get("nickname").toString())
                .userPlan("STARTER")
                .role("ROLE_USER")
                .planBeginAt(LocalDate.now().toString())
                .planFinishAt("9999-12-31")
                .build();
        kakaoUserRepository.save(newMember);

        return new PrincipalDetails(newMember, attributes);

    }

}
