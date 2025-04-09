package com.harshmishra4.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Harsh
 * @created 19/03/2025 - 7:02 PM
 */

@RestController
@RequestMapping("api/v1/home")
public class APIController {

    @GetMapping
    @PreAuthorize("hasRole('SEC_ADMIN')")
    public String welcome() {
        return "Welcome to Spring Security";
    }

}
