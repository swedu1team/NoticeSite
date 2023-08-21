package com.NotificationSite.NotificationSite.repository;

import com.NotificationSite.NotificationSite.entity.OauthUser;
import com.NotificationSite.NotificationSite.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username); // 이미 가입한 유저가 있는지 확인


}