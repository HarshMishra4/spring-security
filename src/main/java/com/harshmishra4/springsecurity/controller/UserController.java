package com.harshmishra4.springsecurity.controller;

import com.harshmishra4.springsecurity.entity.User;
import com.harshmishra4.springsecurity.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Harsh
 * @created 21/03/2025 - 1:57 AM
 */

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllAccounts();
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.authenticateUser(user);
    }
}
