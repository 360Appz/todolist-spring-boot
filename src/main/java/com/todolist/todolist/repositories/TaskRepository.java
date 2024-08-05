package com.todolist.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todolist.todolist.models.Task;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
	
}
