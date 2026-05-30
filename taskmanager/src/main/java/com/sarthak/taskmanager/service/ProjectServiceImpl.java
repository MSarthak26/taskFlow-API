package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.CreateProjectRequest;
import com.sarthak.taskmanager.dto.ProjectResponseDto;
import com.sarthak.taskmanager.entity.Project;
import com.sarthak.taskmanager.entity.User;
import com.sarthak.taskmanager.exception.ResourceNotFoundException;
import com.sarthak.taskmanager.mapper.ProjectMapper;
import com.sarthak.taskmanager.repository.ProjectRepository;
import com.sarthak.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ProjectResponseDto createProject(CreateProjectRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCreatedBy(creator);

        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Project not found"));
        return projectMapper.toDto(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Project not found"));
        projectRepository.delete(project);
    }
}
