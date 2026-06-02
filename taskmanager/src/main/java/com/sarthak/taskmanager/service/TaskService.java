package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.CreateTaskRequest;
import com.sarthak.taskmanager.dto.TaskResponseDto;
import com.sarthak.taskmanager.entity.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(CreateTaskRequest request);

    Page<TaskResponseDto> getAllTasks(int page, int size);

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto updateTask(Long id, CreateTaskRequest request);

    TaskResponseDto updateTaskStatus(Long id, TaskStatus status);

    void deleteTask(Long id);
}
