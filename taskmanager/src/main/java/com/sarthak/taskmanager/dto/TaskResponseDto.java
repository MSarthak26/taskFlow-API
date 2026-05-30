package com.sarthak.taskmanager.dto;

import com.sarthak.taskmanager.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;

    private Long assignedUserId;
    private String assignedUserName;

    private Long projectId;
    private String projectName;
}
