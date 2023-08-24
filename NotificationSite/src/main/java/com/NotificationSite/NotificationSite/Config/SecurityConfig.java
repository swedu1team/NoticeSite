package com.NotificationSite.NotificationSite.Config;



import com.NotificationSite.NotificationSite.service.OAuth2MemberService;
import com.NotificationSite.NotificationSite.service.OAuth2UserService;
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

    private final OAuth2UserService oAuth2UserService;
    private final ObjectMapper objectMapper;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // 시큐리티 로그인 끄기
//                .csrf().disable() // csrf 비활성화
//                .headers((headers) -> headers
//                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
//                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
//                .formLogin((formLogin) -> formLogin
//                        .loginPage("/user/login")
//                        .defaultSuccessUrl("/notice/list"))
//                .oauth2Login(oauth2Configurer -> oauth2Configurer // Oauth 로그인 관련 설정
//                        .loginPage("/login")
//                        .successHandler(successHandler())
//                        .userInfoEndpoint()// 스프링 부트 3.x버젼을 사용하면 userInfoEndpoint메서드 사용 불가 2.7.4로 다운
//                        .userService(oAuth2UserService))
//                .logout((logout) -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 경로
//                        .logoutSuccessUrl("/notice/list")
//                        .invalidateHttpSession(true));
//        return http.build();
//    }

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
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Swagger URL 접근 허용
    private static final String[] URL_TO_PERMIT = {
            "/user/login",
            "/user/signup",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/auth/**"
    };

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
                .and().oauth2Login()
                .loginPage("/login_form")
                .defaultSuccessUrl("/notice/list")
                .userInfoEndpoint()
                .userService(oAuth2MemberService).and().and().build();
    }

}