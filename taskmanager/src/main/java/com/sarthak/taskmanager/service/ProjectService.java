package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.CreateProjectRequest;
import com.sarthak.taskmanager.dto.ProjectResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto createProject(CreateProjectRequest request);
    Page<ProjectResponseDto> getAllProjects(int page,int size);
    ProjectResponseDto getProjectById(Long id);
    void deleteProject(Long id);
}
