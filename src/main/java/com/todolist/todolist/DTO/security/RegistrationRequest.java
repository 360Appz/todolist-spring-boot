package com.todolist.todolist.DTO.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
	
//	This acts as an DTO (Data Transfer Object) and not directly mapped to a database entity, 
//	don't need to include an ID field, especially one that is auto-generated. 
//	The RegistrationRequest is simply a way to capture user input, such as username, password, and email, 
//	and then pass this data to your service layer.
	
	private String taskOwnerName;
    private String username;
    private String password;
    private String email; // Add other fields as needed
}