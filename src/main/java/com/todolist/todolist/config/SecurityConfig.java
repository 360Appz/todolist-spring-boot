
package com.todolist.todolist.config;

import com.todolist.todolist.security.JwtRequestFilter;
import com.todolist.todolist.services.security.MyUserDetailsService;
import com.todolist.todolist.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/v1/security/authenticate", "/api/v1/security/register", "/api/v1/CRUD/**")
            )
            .headers(headers -> headers
                .frameOptions().sameOrigin() // Ensures H2 Console can be loaded
            )
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers( "/api/v1/security/authenticate", "/api/v1/security/register").permitAll()
                .requestMatchers("/api/v1/CRUD/**").authenticated() // Ensure all CRUD endpoints are protected
                .anyRequest().authenticated()
            )
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(myUserDetailsService, jwtUtil);
    }
}
