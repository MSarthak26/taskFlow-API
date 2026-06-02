package com.sarthak.taskmanager.controller;

import com.sarthak.taskmanager.dto.CreateTaskRequest;
import com.sarthak.taskmanager.dto.TaskResponseDto;
import com.sarthak.taskmanager.entity.Task;
import com.sarthak.taskmanager.entity.TaskStatus;
import com.sarthak.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> CreateTask(@Valid @RequestBody CreateTaskRequest request){
        TaskResponseDto task = taskService.createTask(request);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<TaskResponseDto> taskPage =
                taskService.getAllTasks(page, size);

        return ResponseEntity.ok(taskPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id){
        TaskResponseDto task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task with id : " + id + " successfully deleted.", HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status){
        TaskResponseDto task = taskService.updateTaskStatus(id,status);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody CreateTaskRequest request){
        TaskResponseDto task = taskService.updateTask(id,request);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }
}
