package com.todolist.todolist.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.*;
import lombok.Data;


import com.todolist.todolist.models.security.User;

@Entity
@Data
public class Task {
		
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID taskId;
	private String title;
	private String description;
	private LocalDateTime dueDate;
	private String status; //Done , Pending , Overdue
	private LocalDateTime createdAt; //Create time-stamp when task is created
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	 private User user;  // Add this field to link tasks to users
	 
	 @PrePersist
	 protected void onCreate() {
	   createdAt = LocalDateTime.now();
	 } 
	
}
