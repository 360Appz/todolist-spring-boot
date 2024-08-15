package com.todolist.todolist.repositories.search;

import com.todolist.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskSearchRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {
}