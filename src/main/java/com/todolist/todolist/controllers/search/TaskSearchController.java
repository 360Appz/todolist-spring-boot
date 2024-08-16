package com.todolist.todolist.controllers.search;

import com.todolist.todolist.DTO.TaskDTO;
import com.todolist.todolist.services.search.TaskSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory;
import org.springframework.data.web.PagedResourcesAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks/search")
public class TaskSearchController {

    private final TaskSearchService taskSearchService;
    private final PagedResourcesAssembler<TaskDTO> pagedResourcesAssembler;
 

    @Autowired
    public TaskSearchController(TaskSearchService taskSearchService, PagedResourcesAssembler<TaskDTO> pagedResourcesAssembler) {
        this.taskSearchService = taskSearchService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @GetMapping
    public ResponseEntity<?> searchTasks(@RequestParam String keyword, Pageable pageable) {
        //List<TaskDTO> tasks = taskSearchService.searchTasksByKeyword(keyword);
       Page<TaskDTO> tasks = taskSearchService.searchTasksByKeyword(keyword, pageable);
        
        if (tasks.isEmpty()) {
            return ResponseEntity.status(404).body("Task Does Not Exist");
        }
        
        PagedModel<EntityModel<TaskDTO>> pagedModel = pagedResourcesAssembler.toModel(tasks);
        return ResponseEntity.ok(pagedModel);
    }
}