package com.example.EmployeeManagementSystem.backend.mapper;

import com.example.EmployeeManagementSystem.backend.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.backend.entity.EmployeeEntity;

public class EmployeeMapper {
	public static EmployeeEntity toEntity(EmployeeDTO dto) {
		EmployeeEntity emp = new EmployeeEntity();

		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());

		return emp;
	}

	public static EmployeeDTO toDTO(EmployeeEntity emp) {
		EmployeeDTO dto = new EmployeeDTO();

		dto.setName(emp.getName());
		dto.setEmail(emp.getEmail());
		dto.setDepartment(emp.getDepartment());
		dto.setSalary(emp.getSalary());

		return dto;
	}
}
