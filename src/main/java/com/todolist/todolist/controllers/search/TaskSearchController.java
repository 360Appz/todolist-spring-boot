package com.todolist.todolist.controllers.search;

import com.todolist.todolist.DTO.TaskDTO;
import com.todolist.todolist.services.search.TaskSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks/search")
public class TaskSearchController {

    private final TaskSearchService taskSearchService;

    @Autowired
    public TaskSearchController(TaskSearchService taskSearchService) {
        this.taskSearchService = taskSearchService;
    }


    @GetMapping
    public ResponseEntity<?> searchTasks(@RequestParam String keyword) {
        List<TaskDTO> tasks = taskSearchService.searchTasksByKeyword(keyword);
        
        if (tasks.isEmpty()) {
            return ResponseEntity.status(404).body("Task Does Not Exist");
        }
        return ResponseEntity.ok(tasks);
    }
}