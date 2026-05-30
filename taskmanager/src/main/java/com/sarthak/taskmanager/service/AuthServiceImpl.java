package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.LoginRequest;
import com.sarthak.taskmanager.dto.RegisterRequest;
import com.sarthak.taskmanager.dto.UserResponseDto;
import com.sarthak.taskmanager.entity.Role;
import com.sarthak.taskmanager.entity.User;
import com.sarthak.taskmanager.exception.ResourceNotFoundException;
import com.sarthak.taskmanager.exception.UserAlreadyExistsException;
import com.sarthak.taskmanager.mapper.UserMapper;
import com.sarthak.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDto register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );
        if(!matches){
            throw new RuntimeException("Invalid credentials");
        }
        return jwtService.generateToken(user.getEmail());
    }
}
