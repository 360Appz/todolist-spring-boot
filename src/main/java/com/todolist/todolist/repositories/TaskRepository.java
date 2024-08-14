package com.todolist.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todolist.todolist.models.Task;

import java.util.List;
import java.util.UUID;

import com.todolist.todolist.models.security.User;

public interface TaskRepository extends JpaRepository<Task, UUID> {
	 List<Task> findByUser(User user);
	 List<Task> findByUserId(UUID userId); //Gets Task List by User
}
