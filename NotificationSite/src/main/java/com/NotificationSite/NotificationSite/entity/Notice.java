package com.NotificationSite.NotificationSite.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //공지 번호

    private Date meetDay;

    @Column(length = 200)
    private String meetSubject; //공지 제목

    @Column(length = 200)
    private String meetPlace; // 공지 장소

    @Column(length = 200)
    private String content; //공지 내용

    private LocalDateTime createDate;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private SiteUser siteUser;

}