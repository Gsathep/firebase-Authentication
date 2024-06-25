package com.firbase.config.controller;

import com.firbase.config.domain.JwtUser;
import com.firbase.config.controller.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(JwtUser user) {
        userRepository.save(user);
    }

    public void loginUser(String contact, String password) {
        // Logic for login user from database using JWT authentication
    }
}
