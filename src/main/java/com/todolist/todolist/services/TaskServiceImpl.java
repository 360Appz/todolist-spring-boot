package com.todolist.todolist.services;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todolist.todolist.repositories.TaskRepository;

import com.todolist.todolist.services.security.MyUserDetailsService;
import com.todolist.todolist.models.Task;
import com.todolist.todolist.models.security.User;
import com.todolist.todolist.DTO.TaskDTO;
import com.todolist.todolist.exceptions.TaskResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//DTO Implementation
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private TaskDTO convertToDTO(Task task) {
    	
        User currentUser = myUserDetailsService.getCurrentUser(); // Get the currently logged-in user
        task.setUser(currentUser); // Set the user in the DTO
        
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUserId(task.getUser().getId());
        //taskDTO.setUsername(task.getUser().getUsername());
        return taskDTO;
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskId(taskDTO.getTaskId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(taskDTO.getStatus());
        task.setCreatedAt(taskDTO.getCreatedAt());

        User user = myUserDetailsService.getCurrentUser();
        task.setUser(user);

        return task;
    }

    
    public Page<TaskDTO> getTaskListForUser(UUID userId, Pageable pageable) {
        Page<Task> tasks = taskRepo.findByUserId(userId, pageable);
        return tasks.map(this::convertToDTO);
    }

    @Override
    public TaskDTO getTaskById(UUID taskId) {
        return taskRepo.findById(taskId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new TaskResourceNotFoundException("Task not found"));
    }

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = taskRepo.save(task);
        return convertToDTO(savedTask);
    }

    @Override
    public TaskDTO editTask(UUID taskId, TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        task.setTaskId(taskId);
        task.setCreatedAt(LocalDateTime.now());
        Task updatedTask = taskRepo.save(task);
        return convertToDTO(updatedTask);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepo.deleteById(taskId);
    }
}


