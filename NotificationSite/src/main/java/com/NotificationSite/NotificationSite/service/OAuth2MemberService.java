package com.NotificationSite.NotificationSite.service;

import com.NotificationSite.NotificationSite.Oauth.*;
import com.NotificationSite.NotificationSite.entity.SiteUser;
import com.NotificationSite.NotificationSite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class OAuth2MemberService extends DefaultOAuth2UserService {
    private final BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        OAuth2MemberInfo memberInfo = null;
        System.out.println(userRequest.getClientRegistration().getRegistrationId());
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if (registrationId.equals("google")) {
            memberInfo = new GoogleMemberInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("naver")) {
            memberInfo = new NaverMemberInfo(oAuth2User.getAttributes());
        } else if(registrationId.equals("kakao")) {
            memberInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("로그인 실패");
        }
        String provider = memberInfo.getProvider();
        String providerId = memberInfo.getProviderId();
        String username = provider + "_" + providerId; //중복이 발생하지 않도록 provider와 providerId를 조합
        String email = memberInfo.getEmail();
        String role = "ROLE_ADMIN"; //일반 유저
        System.out.println(oAuth2User.getAttributes());
        Optional<SiteUser> findMember = userRepository.findByusername(username);
        SiteUser siteUser = null;
        if (findMember.isEmpty()) { //찾지 못했다면
            siteUser = SiteUser.builder()
                    .name(username)
                    .email(email)
                    .password(encoder.encode("password"))
                    .role(role)
                    .provider(provider)
                    .providerId(providerId).build();
            userRepository.save(siteUser);
        } else {
            siteUser = findMember.get();
        }
        return new PrincipalDetails(siteUser, oAuth2User.getAttributes(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public SiteUser getUser(String username) {
        log.info("시작");
        log.info("username={}",username);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authenticaiton ={}",authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("userDetails ={}",userDetails);
        String name = userDetails.getUsername();
        log.info("name ={}",name);
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        log.info("siteUser = {}", siteUser);
        if (siteUser.isPresent()) {
            log.info("siteUserlast = {}", siteUser);
            return siteUser.get();
        } else {
            return null;
        }
    }

}
