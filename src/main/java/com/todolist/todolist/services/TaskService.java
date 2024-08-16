package com.todolist.todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.todolist.todolist.models.Task;
import com.todolist.todolist.DTO.TaskDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
	
	
	// CRUD Operations (DTO)
	  
	Page<TaskDTO> getTaskListForUser(UUID userId, Pageable pageable); //// Returns DTO instead of entity
	  
	  TaskDTO getTaskById(UUID taskId); // Returns DTO instead of entity
	  
	  TaskDTO saveTask(TaskDTO taskDTO); // Accepts and returns DTO instead of entity
	  
	  TaskDTO editTask(UUID taskId, TaskDTO taskDTO); // Accepts and returns DTO
	  
	  void deleteTask(UUID taskId); // No DTO required for delete
	
				
}
