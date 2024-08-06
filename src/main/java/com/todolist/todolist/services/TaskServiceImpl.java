package com.todolist.todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todolist.todolist.repositories.TaskRepository;
import com.todolist.todolist.models.Task;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepository taskRepo;
	
		
	//Get all tasks
	@Override
	public List<Task> getTaskList()
	{
		//Add pagination
		return taskRepo.findAll();
		
	}
	
	//Create Task
	@Override
	public Task saveTask(Task task)
	{
		return taskRepo.save(task);
	}
	
	// Get Task by ID
	public Optional<Task> getTaskById(UUID taskId)
	{
		return taskRepo.findById(taskId);
	}
	
	
	//Update Task
	public Optional<Task> editTask(UUID taskId, Task updatedTask)
	{
		return taskRepo.findById(taskId).map(existingTask -> {
			existingTask.setTitle(updatedTask.getTitle());
			existingTask.setDescription(updatedTask.getDescription());
			existingTask.setDueDate(updatedTask.getDueDate());
			
			// Update other fields as necessary
			return taskRepo.save(existingTask);
		});
	}
	
	//Delete Task
	public Optional<Task> deleteTask(UUID taskId)
	{
		Optional<Task> taskOptional = taskRepo.findById(taskId);
		taskOptional.ifPresent(taskRepo::delete);
		return taskOptional;
	}
	
	
}
