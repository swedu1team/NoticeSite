package com.NotificationSite.NotificationSite.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;


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

    //username을 toString 함수를 통해서 그대로 출력시킨다.
    @Override
    public String toString(){
        return getUsername();
    }
}