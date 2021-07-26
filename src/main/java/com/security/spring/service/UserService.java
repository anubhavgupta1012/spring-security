package com.security.spring.service;

import com.security.spring.entity.User;

public interface UserService {

    User findUserByUsername(String username);
}
