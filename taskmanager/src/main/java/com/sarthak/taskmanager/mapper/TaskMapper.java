package com.sarthak.taskmanager.mapper;

import com.sarthak.taskmanager.dto.TaskResponseDto;
import com.sarthak.taskmanager.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "assignedTo.id", target = "assignedUserId")
    @Mapping(source = "assignedTo.name", target = "assignedUserName")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.name", target = "projectName")
    TaskResponseDto toDto(Task task);
}
