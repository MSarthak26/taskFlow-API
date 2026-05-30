package com.sarthak.taskmanager.controller;

import com.sarthak.taskmanager.dto.LoginRequest;
import com.sarthak.taskmanager.dto.RegisterRequest;
import com.sarthak.taskmanager.dto.UserResponseDto;
import com.sarthak.taskmanager.entity.User;
import com.sarthak.taskmanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequest request) {
        UserResponseDto user = authService.register(request);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request){
        String token = authService.login(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
