package com.todolist.todolist.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
}
