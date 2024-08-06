package com.todolist.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import com.todolist.todolist.repositories.TaskRepository;
import com.todolist.todolist.services.TaskService;
import com.todolist.todolist.models.Task;
 
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.swing.RepaintManager;


@RestController
@RequestMapping("api/v1")
public class TaskController 
{
	private final TaskService taskService;
	
	@Autowired
	public TaskController (TaskService taskService)
	{
		this.taskService = taskService;
	}
	
	//Get All Task
	@GetMapping("/tasks")
	public List<Task> getAllTasks(Task task)
	{
		//Add pagination
		return taskService.getTaskList();
	}
	
	//Add New Task
	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task)
	{
		Task newTask = taskService.saveTask(task);
		return ResponseEntity.ok(newTask);
	}
	
	//Get Task By ID
	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskByID(@PathVariable UUID id)
	{
		Optional<Task> task = taskService.getTaskById(id);
		return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	//Edit Task
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> editTask (@PathVariable UUID id, @RequestBody Task task)
	{
		Optional<Task> updatedTaskOptional = taskService.editTask(id, task);
		return updatedTaskOptional.map(updatedTask -> ResponseEntity.ok(updatedTask))
				.orElseGet(()->ResponseEntity.notFound().build());
		
	}
	
	//Delete Task
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Task> deleteTask (@PathVariable UUID id)
	{
		Optional<Task> deleteTaskOptional = taskService.deleteTask(id);
		return deleteTaskOptional.map(deleteTask -> ResponseEntity.ok(deleteTask))
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	

}
