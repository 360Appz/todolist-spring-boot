package com.todolist.todolist;

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
			
//			Task task2 = new Task();
//			task2.setTitle("Tesiting2");
//			task2.setDescription("Testing2");
			
			taskService.save(task1);

//			taskService.save(task2);
			
			System.out.println("List of Tasks");
			for(Task task: taskService.getTaskList())
			{
				System.out.println("Task Details"+task);
			}
		};
	}

}
