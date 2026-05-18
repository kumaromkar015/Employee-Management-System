package com.example.EmployeeManagementSystem.backend.mapper;

import com.example.EmployeeManagementSystem.backend.dto.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.backend.dto.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.backend.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeEntity toEntity(EmployeeRequestDTO dto) {
        EmployeeEntity emp = new EmployeeEntity();

        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setPhoneNumber(dto.getPhoneNumber());
        emp.setJobTitle(dto.getJobTitle());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());

        return emp;
    }

    public EmployeeResponseDTO toResponseDto(EmployeeEntity emp) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(emp.getId());
        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setEmail(emp.getEmail());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setJobTitle(emp.getJobTitle());
        dto.setDepartment(emp.getDepartment());

        dto.setCreatedAt(emp.getCreateAt());
        dto.setUpdatedAt(emp.getUpdateAt());

        return dto;
    }

    public void updateEntityFromDto(
            EmployeeRequestDTO requestDto,
            EmployeeEntity employee           // existing entity from DB
    ) {
        employee.setFirstName(requestDto.getFirstName());
        employee.setLastName(requestDto.getLastName());
        employee.setEmail(requestDto.getEmail());
        employee.setPhoneNumber(requestDto.getPhoneNumber());
        employee.setDepartment(requestDto.getDepartment());
        employee.setJobTitle(requestDto.getJobTitle());
        employee.setSalary(requestDto.getSalary());

        // id stays the same — we're updating, not creating
        // createdAt stays the same — it's the original creation time
        // updatedAt → @UpdateTimestamp will auto-update it
    }
}
