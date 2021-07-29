package com.security.spring.config;

import com.security.spring.filter.CustomFilter;
import com.security.spring.provider.CustomAuthenticationProvider;
import com.security.spring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private CustomFilter customFilter;

    @Bean
    public UserDetailsService getUserDetails() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPassWordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider).eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        * Since we are adding our CustomFilter and delegating request to AuthenticationManager So
        * Spring Security default Login Page won't be shown. But if we just propagate the request
        * by doing  filterChain.doFilter(servletRequest, servletResponse); and from our CustomFilter we do not delegate
        * to AuthenticationManger it will show the Spring Security default Login Page
        */
        http.addFilterAt(customFilter, BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
