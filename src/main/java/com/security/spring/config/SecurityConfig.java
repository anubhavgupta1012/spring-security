package com.security.spring.config;

import com.security.spring.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetails() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPassWordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
