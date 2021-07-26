package com.security.spring.service.impl;

import com.security.spring.entity.User;
import com.security.spring.repo.UserRepo;
import com.security.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
