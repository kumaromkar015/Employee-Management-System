package com.example.EmployeeManagementSystem.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeManagementSystem.backend.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

}
