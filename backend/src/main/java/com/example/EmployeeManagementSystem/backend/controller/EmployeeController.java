package com.example.EmployeeManagementSystem.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeManagementSystem.backend.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping
	public List<EmployeeDTO> getAll() {
		return service.getAll();
	}

	@PostMapping
	public EmployeeDTO add(@Valid @RequestBody EmployeeDTO dto) {
		return service.save(dto);
	}

	@PutMapping("/{id}")
	public EmployeeDTO update(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
