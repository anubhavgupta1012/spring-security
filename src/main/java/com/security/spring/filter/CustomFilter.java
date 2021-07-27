package com.security.spring.filter;

import com.security.spring.pojo.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class CustomFilter implements Filter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Based upon the request
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String auth_key = request.getHeader("auth_key");

        //create Authentication Object
        CustomAuthentication customAuthentication = new CustomAuthentication(auth_key, null);

        //delegate the request to Authentication Provider
        Authentication authentication = authenticationManager.authenticate(customAuthentication);

        // Saving the Authentication Object In Context
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
