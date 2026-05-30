package com.sarthak.taskmanager.dto;

import com.sarthak.taskmanager.entity.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
