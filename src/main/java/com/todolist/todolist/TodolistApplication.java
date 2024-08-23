package com.todolist.todolist;

import java.time.LocalDateTime;
import java.util.UUID;

import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.todolist.todolist.models.Task;
import com.todolist.todolist.services.TaskService;
import com.todolist.todolist.repositories.security.UserRepository;
import com.todolist.todolist.models.security.User;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

//	@Bean
//    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            String username = "admin";
//            String password = "admin";
//
//            // Check if the user already exists
//            if (!userRepository.findByUsername(username).isPresent()) {
//                User user = User.builder()
//                        .id(UUID.randomUUID())
//                        .username(username)
//                        .password(passwordEncoder.encode(password))
//                        .build();
//                userRepository.save(user);
//                System.out.println("User created: " + user);
//            } else {
//                System.out.println("User with username '" + username + "' already exists.");
//            }
//        };
//    }
	
//	@Bean
//	public CommandLineRunner task(TaskService taskService)
//	{
//		return(args) ->
//		{
//			Task task1 = new Task();
//			task1.setTitle("Testing");
//			task1.setDescription("Testing2");
//			task1.setDueDate(LocalDateTime.of(2024, 8, 15, 10, 30));
//			task1.setStatus("Done");
//			
//			
//			taskService.saveTask(task1);
//
//			
//			System.out.println("List of Tasks");
//			for(Task task: taskService.getTaskList())
//			{
//				System.out.println("Task Details"+task);
//			}
//		};
//	}

}
