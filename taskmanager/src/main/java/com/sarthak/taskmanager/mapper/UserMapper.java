package com.sarthak.taskmanager.mapper;

import com.sarthak.taskmanager.dto.UserResponseDto;
import com.sarthak.taskmanager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
}
