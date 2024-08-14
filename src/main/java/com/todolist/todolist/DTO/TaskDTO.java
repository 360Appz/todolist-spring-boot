package com.todolist.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private UUID taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String status;
    private LocalDateTime createdAt;
    private UUID userId;  // To reference the user who created the task
}