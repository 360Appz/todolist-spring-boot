package com.todolist.todolist.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;



import com.todolist.todolist.models.security.User;
import com.todolist.todolist.repositories.security.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
