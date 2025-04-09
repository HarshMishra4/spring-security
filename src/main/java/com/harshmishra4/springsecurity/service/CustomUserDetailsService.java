package com.harshmishra4.springsecurity.service;

import com.harshmishra4.springsecurity.entity.User;
import com.harshmishra4.springsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Harsh
 * @created 21/03/2025 - 2:41 AM
 */

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            System.out.printf("User not found: %s\n", username);
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
