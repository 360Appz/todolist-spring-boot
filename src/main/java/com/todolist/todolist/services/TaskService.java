package com.todolist.todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.todolist.todolist.models.Task;
import com.todolist.todolist.DTO.TaskDTO;

public interface TaskService {
	
	
	// CRUD Operations (DTO)
	  
	  List<TaskDTO> getTaskListForUser(UUID userId); //// Returns DTO instead of entity
	  
	  TaskDTO getTaskById(UUID taskId); // Returns DTO instead of entity
	  
	  TaskDTO saveTask(TaskDTO taskDTO); // Accepts and returns DTO instead of entity
	  
	  TaskDTO editTask(UUID taskId, TaskDTO taskDTO); // Accepts and returns DTO
	  
	  void deleteTask(UUID taskId); // No DTO required for delete
	
				
}
