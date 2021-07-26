package com.security.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetails() {
        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User
            .withUsername("qwerty")
            .password("qwerty")
            .authorities("read")
            .build();
        detailsManager.createUser(userDetails);
        return detailsManager;
    }

    @Bean
    public PasswordEncoder getPassWordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
