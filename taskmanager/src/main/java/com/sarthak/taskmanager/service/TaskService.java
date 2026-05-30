package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.CreateTaskRequest;
import com.sarthak.taskmanager.dto.TaskResponseDto;
import com.sarthak.taskmanager.entity.Task;
import com.sarthak.taskmanager.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(CreateTaskRequest request);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto updateTask(Long id, CreateTaskRequest request);

    TaskResponseDto updateTaskStatus(Long id, TaskStatus status);

    void deleteTask(Long id);
}
