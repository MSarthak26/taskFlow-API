package com.sarthak.taskmanager.mapper;

import com.sarthak.taskmanager.dto.ProjectResponseDto;
import com.sarthak.taskmanager.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponseDto toDto(Project project);
}