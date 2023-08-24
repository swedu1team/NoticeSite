package com.NotificationSite.NotificationSite.repository;

import com.NotificationSite.NotificationSite.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuth2MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByName(String name);
}
