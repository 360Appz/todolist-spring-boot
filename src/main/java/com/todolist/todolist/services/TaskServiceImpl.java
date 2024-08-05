package com.todolist.todolist.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todolist.todolist.repositories.TaskRepository;
import com.todolist.todolist.models.Task;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepository taskRepo;
	
	@Override
	public void save(Task task)
	{
		taskRepo.save(task);
	}
	
	@Override
	public List<Task> getTaskList()
	{
		return taskRepo.findAll();
	}

	
	
}
