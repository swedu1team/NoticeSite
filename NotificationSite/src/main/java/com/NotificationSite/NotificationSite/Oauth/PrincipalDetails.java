package com.NotificationSite.NotificationSite.Oauth;

import com.NotificationSite.NotificationSite.entity.SiteUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements OAuth2User, UserDetails {
    private SiteUser siteUser;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;


    public PrincipalDetails(SiteUser siteUser, Collection<? extends GrantedAuthority> authorities) {
        this.siteUser = siteUser;
        this.authorities = authorities;
    }

    public PrincipalDetails(SiteUser siteUser, Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities) {
        this.siteUser = siteUser;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true; // 사용자 계정이 활성화되어 있는지 여부를 반환
    }

    @Override
    public String getPassword() {
        return null; // 비밀번호를 반환
    }

    @Override
    public String getUsername() {
        return siteUser.getUsername(); // 사용자의 이름을 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되었는지 여부를 반환
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정이 잠겨있는지 여부를 반환
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호가 만료되었는지 여부를 반환
    }

    @Override
    public String getName() {
        return siteUser.getUsername(); // 사용자의 이름을 반환
    }
}
