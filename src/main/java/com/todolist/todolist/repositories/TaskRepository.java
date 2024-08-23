package com.todolist.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todolist.todolist.models.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import com.todolist.todolist.models.security.User;

public interface TaskRepository extends JpaRepository<Task, UUID> {
	 //List<Task> findByUser(User user);
	 Page<Task> findByUserId(UUID userId , Pageable pageable); //Gets Task List by User
}
