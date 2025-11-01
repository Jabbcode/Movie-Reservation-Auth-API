package com.movie_app.movie.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public String login() {
        return "Login";
    }
}
