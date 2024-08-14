package com.todolist.todolist.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import com.todolist.todolist.DTO.security.RegistrationRequest;
import com.todolist.todolist.models.security.User;
import com.todolist.todolist.repositories.security.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    // @Lazy This tells Spring to only initialize the PasswordEncoder 
    // bean when it's actually needed, thus breaking the dependency cycle.
    public MyUserDetailsService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
  
        
    }
    
    
    	//For Spring security user authentication
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                new ArrayList<>()
	        );
	    }
	 
	    // Method to get the currently authenticated user, for task creation/retrieval
	    public User getCurrentUser() {
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        return userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	    }
	    
	    
	    //Create New User
	    public ResponseEntity<String> saveNewUser(RegistrationRequest registrationRequest) {
	        // Check if user already exists
	        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
	        }

	        User user = new User();
	        user.setUsername(registrationRequest.getUsername());
	        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
	        user.setEmail(registrationRequest.getEmail());


	        userRepository.save(user);

	        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	    }

}
