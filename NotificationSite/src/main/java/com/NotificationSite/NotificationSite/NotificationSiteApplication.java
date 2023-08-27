package com.NotificationSite.NotificationSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationSiteApplication.class, args);
	}

	@Bean
	public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
}
