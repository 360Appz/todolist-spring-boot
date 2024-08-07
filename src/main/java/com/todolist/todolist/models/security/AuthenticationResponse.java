package com.todolist.todolist.models.security;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse 
{
    private final String jwt;
}