package com.Spotivent.SpotiventBackend.users.controller;

import com.Spotivent.SpotiventBackend.response.Response;
import com.Spotivent.SpotiventBackend.users.dto.RegisterRequestDTO;
import com.Spotivent.SpotiventBackend.users.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        return Response.success("Get profile success", userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return Response.success("User registered successfully", userService.register(registerRequestDTO));
    }
}