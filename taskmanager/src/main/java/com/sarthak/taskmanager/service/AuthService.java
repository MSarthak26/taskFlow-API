package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.LoginRequest;
import com.sarthak.taskmanager.dto.RegisterRequest;
import com.sarthak.taskmanager.dto.UserResponseDto;
import com.sarthak.taskmanager.entity.User;

public interface AuthService {
    UserResponseDto register(RegisterRequest request);
    String login(LoginRequest request);
}
