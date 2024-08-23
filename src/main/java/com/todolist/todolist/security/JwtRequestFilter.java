package com.todolist.todolist.security;

import com.todolist.todolist.services.security.MyUserDetailsService;
import com.todolist.todolist.utils.JwtUtil;

import io.jsonwebtoken.lang.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	 
    private final UserDetailsService  myUserDetailsService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(UserDetailsService myUserDetailsService, JwtUtil jwtUtil) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        

        logger.info("Processing request to: {}", request.getRequestURI());
        logger.info("Authorization Header: {}", authorizationHeader);

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            logger.info("Extracted JWT from Header: {}", jwt); // Log the JWT extracted
            username = jwtUtil.extractUsername(jwt);
            logger.info("Extracted Username from JWT: {}", username); 
        }
        else {
            logger.warn("Authorization header is missing or does not start with Bearer ");
        }
        
       

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
            
            // Log the user details for debugging
            logger.info("Loaded user details: username: {}, authorities: {}", userDetails.getUsername(), userDetails.getAuthorities());

            if (jwtUtil.validateToken(jwt, userDetails)) { // Adjusted to use UserDetails object

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
