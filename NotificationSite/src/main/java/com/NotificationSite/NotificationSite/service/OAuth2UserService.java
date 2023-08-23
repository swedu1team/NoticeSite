package com.NotificationSite.NotificationSite.service;


import com.NotificationSite.NotificationSite.entity.OauthUser;
import com.NotificationSite.NotificationSite.entity.SiteUser;
import com.NotificationSite.NotificationSite.repository.OauthRepository;
import com.NotificationSite.NotificationSite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final OauthRepository oauthRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 기본 OAuth2 사용자 정보를 가져옵니다.
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 권한 생성: "ROLE_USER" 권한을 부여합니다.
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        // OAuth2 제공자에서 사용자 이름을 가져올 수 있는 속성 이름을 가져옵니다.
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // DB에 저장
        OauthUser oauthUser = new OauthUser();
        oauthUser.setUsername(oAuth2User.getAttribute("username"));
        oauthUser.setEmail(oAuth2User.getAttribute("email"));
        oauthUser.setPicture(oAuth2User.getAttribute("picture"));
        oauthRepository.save(oauthUser);

        // OAuth2 사용자 정보와 권한 정보를 바탕으로 DefaultOAuth2User 객체를 생성하여 반환합니다.
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
    }
}
