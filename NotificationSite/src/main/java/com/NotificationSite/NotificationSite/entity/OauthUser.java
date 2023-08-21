package com.NotificationSite.NotificationSite.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@Setter
public class OauthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String picture;

}
