package com.todolist.todolist.controllers.security;

import com.todolist.todolist.DTO.security.RegistrationRequest;
import com.todolist.todolist.services.security.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
        	  logger.info("Received Registration Request: {}", registrationRequest);

              // Create a new user and handle conflicts inside saveNewUser
              ResponseEntity<?> response = myUserDetailsService.saveNewUser(registrationRequest);
              
              // Check if the registration was successful based on the response from saveNewUser
              if (response.getStatusCode().is2xxSuccessful()) {
                  return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
              } else {
                  return response; // Return the original response from saveNewUser if not successful
              }

        } catch (Exception e) {
            logger.error("Error during registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}