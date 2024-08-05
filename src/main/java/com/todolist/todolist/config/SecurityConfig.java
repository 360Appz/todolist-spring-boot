package com.todolist.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                    .requestMatchers("/h2-console/**").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .csrf(csrf -> csrf
//                .ignoringRequestMatchers("/h2-console/**")
//            )
//            .headers(headers -> headers
//                .frameOptions().sameOrigin() // Ensures H2 Console can be loaded
//            )
//            .formLogin(withDefaults()); // Default form login configuration
//        
//        return http.build();
//    }
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
	                .requestMatchers("/h2-console/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            .csrf(csrf -> csrf
	                .ignoringRequestMatchers("/h2-console/**")
	            )
	            .headers(headers -> headers
	                .frameOptions().sameOrigin() // Ensures H2 Console can be loaded
	            )
	            .formLogin(formLogin -> formLogin
	                .loginPage("/login")
	                .permitAll()
	            ) // Default form login configuration
	            .logout(logout -> logout
	                .permitAll()
	            );

	        return http.build();
	    }
	
}
