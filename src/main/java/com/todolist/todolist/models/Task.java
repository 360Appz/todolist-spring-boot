package com.todolist.todolist.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Task {
		
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID taskId;
	private String title;
	private String description;
	private LocalDateTime dueDate;
	private String status;
	private LocalDateTime createdAt; //Create timestamp when task is created
	 
	 @PrePersist
	 protected void onCreate() {
	   createdAt = LocalDateTime.now();
	 } 
	
}
