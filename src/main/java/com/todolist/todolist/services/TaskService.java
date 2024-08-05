package com.todolist.todolist.services;

import java.util.List;
import com.todolist.todolist.models.Task;

public interface TaskService {
	void save(Task task);
	List<Task> getTaskList();
	
	
			
}
