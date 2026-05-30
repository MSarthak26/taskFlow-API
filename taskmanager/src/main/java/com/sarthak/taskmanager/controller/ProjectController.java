package com.sarthak.taskmanager.controller;

import com.sarthak.taskmanager.dto.CreateProjectRequest;
import com.sarthak.taskmanager.dto.ProjectResponseDto;
import com.sarthak.taskmanager.entity.Project;
import com.sarthak.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody CreateProjectRequest request){
        ProjectResponseDto project = projectService.createProject(request);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
        List<ProjectResponseDto> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id){
        ProjectResponseDto project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return new ResponseEntity<>("Project with id : " + id + " successfully deleted.", HttpStatus.OK);
    }

}
