package com.harshmishra4.springsecurity.service;

import com.harshmishra4.springsecurity.entity.User;
import com.harshmishra4.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Harsh
 * @created 21/03/2025 - 4:23 AM
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllAccounts() {
        return userRepository.findAll();
    }

    public String authenticateUser(User user) {
        User user1 = userRepository.findByName(user.getName());
        if (user1.getPassword().equals(user.getPassword())) {
            return "success";
        }
        return "fail";
    }
}
