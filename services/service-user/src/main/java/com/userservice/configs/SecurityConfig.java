package com.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Security Config
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
            http
            .csrf(Csrf -> Csrf.disable())
            .formLogin(formLogin -> formLogin.disable())
            .build();
    }


}
