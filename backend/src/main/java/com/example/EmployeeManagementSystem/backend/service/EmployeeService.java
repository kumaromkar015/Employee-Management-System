package com.example.EmployeeManagementSystem.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmployeeManagementSystem.backend.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.backend.entity.EmployeeEntity;
import com.example.EmployeeManagementSystem.backend.mapper.EmployeeMapper;
import com.example.EmployeeManagementSystem.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repo;

	@Autowired
	public EmployeeService(EmployeeRepository repo) {
		this.repo = repo;
	}

	// Get All
	public List<EmployeeDTO> getAll() {
		return repo.findAll().stream().map(emp -> EmployeeMapper.toDTO(emp)).collect(Collectors.toList());
	}

	// ADD
	public EmployeeDTO save(EmployeeDTO dto) {
		EmployeeEntity emp = EmployeeMapper.toEntity(dto);

		EmployeeEntity saved = repo.save(emp);
		return EmployeeMapper.toDTO(saved);

	}

	// Delete
	public void delete(Long id) {
		repo.deleteById(id);
	}

	// update
	public EmployeeDTO update(Long id, EmployeeDTO dto) {
		EmployeeEntity emp = repo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found"));

		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());

		return EmployeeMapper.toDTO(repo.save(emp));
	}
}
