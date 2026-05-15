package com.example.EmployeeManagementSystem.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDTO {
	
	@NotBlank(message = "Name not found")
	private String name;
	
	@Email(message = "Email not valid")
	@NotBlank
	private String email;
	
	@NotBlank
	private String department;
	
	@Positive
	private double salary;


	
	
}
