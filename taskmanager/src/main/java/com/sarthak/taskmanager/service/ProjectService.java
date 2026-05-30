package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.CreateProjectRequest;
import com.sarthak.taskmanager.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto createProject(CreateProjectRequest request);
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(Long id);
    void deleteProject(Long id);
}
