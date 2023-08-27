package com.NotificationSite.NotificationSite.entity;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class SiteUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String role; //유저 권한 (일반 유저, 관리지ㅏ)
    private String provider; //공급자 (google, facebook ...)
    private String providerId; //공급 아이디

    @Builder
    public SiteUser(String name, String password, String email, String role, String provider, String providerId) {
        this.username = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public String getRole() {
        // 여기서 역할 정보를 가져오거나 반환하는 로직을 작성해야 합니다.
        // 예를 들어, "ROLE_USER"를 반환하거나 사용자의 역할 정보를 반환하도록 구현합니다.
        return "ROLE_USER";
    }

    //username을 toString 함수를 통해서 그대로 출력시킨다.
    @Override
    public String toString(){
        return getUsername();
    }
}