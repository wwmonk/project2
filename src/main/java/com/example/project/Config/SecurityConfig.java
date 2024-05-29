package com.example.project.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationFailureHandler customFailureHandler;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth)-> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/member/save").permitAll();
                    auth.requestMatchers("/member/update").permitAll();
                    auth.requestMatchers("/member/delete").permitAll();
                    auth.requestMatchers("/main").permitAll();
                    auth.requestMatchers("/assets/**","/css/**", "/js/**", "/img/**").permitAll();
                    auth.requestMatchers("/chfood").permitAll();
                    auth.requestMatchers("/japan").permitAll();
                    auth.requestMatchers("/food").permitAll();
                    auth.requestMatchers("/booking/list").permitAll();
                    auth.requestMatchers("/booking/save").permitAll();
                    auth.requestMatchers("/booking/update").permitAll();
                    auth.requestMatchers("/address").permitAll();
                    auth.requestMatchers("/booking/delete").permitAll();
        });
        http.formLogin(login-> login
                .loginPage("/login")
                .failureHandler(customFailureHandler)
                .usernameParameter("username")
                .defaultSuccessUrl("/main", true)
                .permitAll()
        );
        //로그아웃설정(기본 /logout)
        http.logout(logout->logout
                .logoutSuccessUrl("/index")
        );
        //csrf 사용X
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}



