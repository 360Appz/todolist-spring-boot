package com.todolist.todolist;

import java.time.LocalDateTime;

import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.todolist.todolist.models.Task;
import com.todolist.todolist.services.TaskService;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
	@Bean
	public CommandLineRunner task(TaskService taskService)
	{
		return(args) ->
		{
			Task task1 = new Task();
			task1.setTitle("Testing");
			task1.setDescription("Testing2");
			task1.setDueDate(LocalDateTime.of(2024, 8, 15, 10, 30));
			
			
			taskService.saveTask(task1);

			
			System.out.println("List of Tasks");
			for(Task task: taskService.getTaskList())
			{
				System.out.println("Task Details"+task);
			}
		};
	}

}
