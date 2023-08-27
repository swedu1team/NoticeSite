package com.NotificationSite.NotificationSite.Config;



import com.NotificationSite.NotificationSite.service.OAuth2MemberService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 스프링 시큐리티 설정 클래스
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final ObjectMapper objectMapper;


    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // JSON 응답 대신 리다이렉트를 수행
//                response.sendRedirect("/notice/noticewrite"); // 원하는 페이지로 수정

                // 기존의 JSON 응답 부분은 주석 처리

            }
        };
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    private final OAuth2MemberService oAuth2MemberService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/private/**")).authenticated() // 수정된 부분
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).access("hasRole('ROLE_ADMIN')") //admin으로 시작하는 uri는 관릴자 계정만 접근 가능
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login")        // 인증이 필요한 URL에 접근하면 /loginForm으로 이동
                .loginProcessingUrl("/user/login")        // 로그인을 처리할 URL 입력
                .defaultSuccessUrl("/notice/list")            // 로그인 성공하면 "/" 으로 이동
                .failureUrl("/user/login")
                .and()
                .logout()                    // logout할 경우
                .logoutUrl("/user/logout")            // 로그아웃을 처리할 URL 입력
                .logoutSuccessUrl("/notice/list")
                .and().oauth2Login()
                .loginPage("/login_form")
                .defaultSuccessUrl("/notice/list")
                .userInfoEndpoint()
                .userService(oAuth2MemberService).and().and().build();
    }

}