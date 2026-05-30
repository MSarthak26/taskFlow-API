package com.sarthak.taskmanager.service;

import com.sarthak.taskmanager.dto.CreateTaskRequest;
import com.sarthak.taskmanager.dto.TaskResponseDto;
import com.sarthak.taskmanager.entity.Project;
import com.sarthak.taskmanager.entity.Task;
import com.sarthak.taskmanager.entity.TaskStatus;
import com.sarthak.taskmanager.entity.User;
import com.sarthak.taskmanager.exception.ResourceNotFoundException;
import com.sarthak.taskmanager.mapper.TaskMapper;
import com.sarthak.taskmanager.repository.ProjectRepository;
import com.sarthak.taskmanager.repository.TaskRepository;
import com.sarthak.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskResponseDto createTask(CreateTaskRequest request) {
        User assignedTo = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Task task = new Task();
        task.setAssignedTo(assignedTo);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProject(project);
        task.setStatus(TaskStatus.TODO);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found"));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, CreateTaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found"));

        Long projectId = request.getProjectId();
        Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("project not found"));

        Long userId = request.getAssignedUserId();
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));

        task.setDescription(request.getDescription());
        task.setProject(project);
        task.setTitle(request.getTitle());
        task.setAssignedTo(user);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found"));
        task.setStatus(status);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
