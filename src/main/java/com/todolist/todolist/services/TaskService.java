package com.todolist.todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.todolist.todolist.models.Task;

public interface TaskService {
	
	//CRUD operations
	
	List<Task> getTaskList(); //Get All Tasks
	
	Task saveTask(Task task); //Add Task
	
	Optional<Task> getTaskById(UUID taskId); //Get Task by ID
	

	Optional<Task> editTask(UUID taskId, Task updatedTask); //Edit Task
	
	Optional<Task> deleteTask(UUID taskId); //Delete Task
	
	
			
}
