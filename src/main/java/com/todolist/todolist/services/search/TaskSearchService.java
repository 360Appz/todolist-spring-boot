package com.todolist.todolist.services.search;

import com.todolist.todolist.DTO.TaskDTO;
import com.todolist.todolist.models.Task;
import com.todolist.todolist.models.security.User;
import com.todolist.todolist.repositories.search.TaskSearchRepository;
import com.todolist.todolist.services.security.MyUserDetailsService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskSearchService {

    private final TaskSearchRepository taskSearchRepository;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public TaskSearchService(TaskSearchRepository taskSearchRepository, MyUserDetailsService myUserDetailsService) {
    	
        this.taskSearchRepository = taskSearchRepository;
        this.myUserDetailsService = myUserDetailsService;
    }
    
    public Page<TaskDTO> searchTasksByKeyword(String keyword, Pageable pageable) {
    	
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = myUserDetailsService.getCurrentUser();
        
        Specification<Task> spec = (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("user"), currentUser),
                criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), "%" + keyword + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + keyword + "%")
                )
            );

        	//List<Task> tasks = taskSearchRepository.findAll(spec);
            Page<Task> tasks = taskSearchRepository.findAll(spec, pageable);

            return tasks.map(task -> TaskDTO.builder()
                    		.taskId(task.getTaskId())
                            .title(task.getTitle())
                            .description(task.getDescription())
                            .dueDate(task.getDueDate())
                            .status(task.getStatus())
                            .createdAt(task.getCreatedAt())
                            .userId(task.getUser().getId())
                            .build());
                    //.collect(Collectors.toList());
        

    }
}