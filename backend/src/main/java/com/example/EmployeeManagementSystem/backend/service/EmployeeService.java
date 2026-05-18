package com.example.EmployeeManagementSystem.backend.service;

import com.example.EmployeeManagementSystem.backend.dto.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.backend.dto.EmployeeResponseDTO;
import org.springframework.data.domain.Page;


public interface EmployeeService {


    // ─── CREATE ───────────────────────────────────────────────
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDto);


    // ─── READ (single) ────────────────────────────────────────
    EmployeeResponseDTO getEmployeeById(Long id);


    // ─── READ (all with pagination + sorting) ─────────────────
    Page<EmployeeResponseDTO> getAllEmployees(
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir
    );


    // ─── READ (search with pagination) ────────────────────────
    Page<EmployeeResponseDTO> searchEmployees(
            String keyword,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir
    );


    // ─── UPDATE ───────────────────────────────────────────────
    EmployeeResponseDTO updateEmployee(
            Long id,
            EmployeeRequestDTO requestDto
    );


    // ─── DELETE ───────────────────────────────────────────────
    void deleteEmployee(Long id);
}
