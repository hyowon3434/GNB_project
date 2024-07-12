package com.example.gnb.config.oauth;

import com.example.gnb.config.auth.PrincipalDetails;
import com.example.gnb.user.entity.KakaoUser;
import com.example.gnb.user.repository.KakaoUserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private KakaoUserRepository kakaoUserRepository;
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String client_id;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirect_uri;
    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String token_url;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String user_info_url;
    public OAuth2AuthenticationToken getKakaoUser(String code){
        OAuth2User oAuth2User = loadUserFromKakao(code);

        KakaoUser kakaoUser = kakaoUserRepository.findByEmail(oAuth2User.getAttribute("email"));

//        if (kakaoUser == null) {
//            joinKakaoUser(oAuth2User);
//        }
        return new OAuth2AuthenticationToken(
                new DefaultOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority("ROLE)USER")),
                        oAuth2User.getAttributes(),
                        "id"
                ),
                Collections.singleton(new SimpleGrantedAuthority("ROILE_USER")),
                "id"
        );
    }

    private OAuth2User loadUserFromKakao(String code){
        String accessToken = getAccessTokenFromKakao(code);
        Map<String, Object> userAttributes = getUserAttributesFromKakao(accessToken);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                userAttributes,
                "id"
        );
    }

    private String getAccessTokenFromKakao(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        log.warn("redirect_uri = !!!! " + redirect_uri);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_uri", redirect_uri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                token_url,
                request,
                Map.class
        );

        return response.getBody().get("access_token").toString();
    }

    private Map<String, Object> getUserAttributesFromKakao(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                user_info_url,
                HttpMethod.GET,
                request,
                Map.class
        );
        return response.getBody();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.warn("나 loadUser 호출함!!!");
        if (registrationId.equals("kakao")) {
            return joinKakaoUser(oAuth2User);
        }

        return oAuth2User;
    }

    private OAuth2User joinKakaoUser(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");
        log.warn("나 joinKakaoUser 호출함");
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
