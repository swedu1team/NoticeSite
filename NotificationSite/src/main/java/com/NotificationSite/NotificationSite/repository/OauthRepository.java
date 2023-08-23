package com.NotificationSite.NotificationSite.repository;

import com.NotificationSite.NotificationSite.entity.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthRepository extends JpaRepository<OauthUser, Long> {

    Optional<OauthUser> findByUsername(String username);
}
