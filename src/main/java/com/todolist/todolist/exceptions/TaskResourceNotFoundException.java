package com.todolist.todolist.exceptions;

public class TaskResourceNotFoundException extends RuntimeException {
    public TaskResourceNotFoundException(String message) {
        super(message);
    }
}