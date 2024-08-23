package com.todolist.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todolist.todolist.services.TaskService;
import com.todolist.todolist.services.security.MyUserDetailsService;
import com.todolist.todolist.models.security.User;
import com.todolist.todolist.DTO.TaskDTO;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;


import java.util.List;
import java.util.UUID;


//CRUD Controller
@RestController
@RequestMapping("api/v1/CRUD")
public class TaskController {

    private final TaskService taskService;
    private final MyUserDetailsService myUserDetailsService;
    private final PagedResourcesAssembler<TaskDTO> pagedResourcesAssembler;

    @Autowired
    public TaskController(TaskService taskService, MyUserDetailsService myUserDetailsService, PagedResourcesAssembler<TaskDTO> pagedResourcesAssembler) {
        this.taskService = taskService;
        this.myUserDetailsService = myUserDetailsService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<PagedModel<EntityModel<TaskDTO>>> getAllTasks(Pageable pageable) {
        
        UUID currentUserId = myUserDetailsService.getCurrentUser().getId(); // Get the currently logged-in user
        Page<TaskDTO> tasks = taskService.getTaskListForUser(currentUserId, pageable);
        PagedModel<EntityModel<TaskDTO>> pagedModel = pagedResourcesAssembler.toModel(tasks);
        return ResponseEntity.ok(pagedModel);

        
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
    	
    	  User currentUser = myUserDetailsService.getCurrentUser(); // Get the currently logged-in user
          taskDTO.setUserId(currentUser.getId()); // Set the user ID in the DTO
        
        TaskDTO newTask = taskService.saveTask(taskDTO);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<TaskDTO> getTaskByID(@PathVariable UUID id) {
    	        
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/editTask/{id}")
    public ResponseEntity<TaskDTO> editTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) {
    	

    	  User currentUser = myUserDetailsService.getCurrentUser(); // Get the currently logged-in user
          taskDTO.setUserId(currentUser.getId()); // Set the user ID in the DTO
        
        TaskDTO updatedTask = taskService.editTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id) {
    	
    	 User currentUser = myUserDetailsService.getCurrentUser(); // Get the currently logged-in user
    	  TaskDTO task = taskService.getTaskById(id);

    	    // Check if the task belongs to the current user
    	    if (!task.getUserId().equals(currentUser.getId())) {
    	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Return 403 Forbidden if the task does not belong to the current user
    	    }
        
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task successfully deleted.");
    }
}


