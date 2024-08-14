package com.todolist.todolist.DTO.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest 
{
    private String username;
    private String password;
    private String email; 
}