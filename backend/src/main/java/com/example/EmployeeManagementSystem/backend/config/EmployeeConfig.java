package com.example.EmployeeManagementSystem.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class EmployeeConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {

		http.csrf(csrf -> csrf.disable()).cors(cors -> {
		}) // IMPORTANT
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		return http.build();
	}
}
