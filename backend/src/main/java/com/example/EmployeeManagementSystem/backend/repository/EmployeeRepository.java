package com.example.EmployeeManagementSystem.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeManagementSystem.backend.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    // ─── Method 1: Derived Query ──────────────────────────────
    // Spring reads method name and generates SQL automatically
    // findBy + FieldName = WHERE field_name = ?

    Optional<EmployeeEntity> findByEmail(String email);
    // Generated SQL:
    // SELECT * FROM employees WHERE email = ?
    // Returns Optional — forces caller to handle "not found" case
    // Use case: Check if email already exists before saving


    // ─── Method 2: Derived Query with boolean ─────────────────

    boolean existsByEmail(String email);
    // Generated SQL:
    // SELECT COUNT(*) > 0 FROM employees WHERE email = ?
    // Use case: Validate duplicate email during registration
    // Faster than findByEmail — doesn't fetch full object


    // ─── Method 3: Search with Pagination ─────────────────────
    // This is our SEARCH feature
    // Searches across multiple fields
    // Returns Page<Employee> — includes data + pagination metadata

    @Query("""
            SELECT e FROM EmployeeEntity e
            WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(e.lastName)  LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(e.email)     LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(e.department)LIKE LOWER(CONCAT('%', :keyword, '%'))
               OR LOWER(e.jobTitle)  LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    Page<EmployeeEntity> searchEmployees(
            @Param("keyword") String keyword,
            Pageable pageable
    );
    // This is JPQL — Java Persistence Query Language
    // Looks like SQL but uses CLASS names and FIELD names
    // not table names and column names
    //
    // LOWER() = case-insensitive search
    // CONCAT('%', :keyword, '%') = contains search (like *keyword*)
    // @Param("keyword") links method param to :keyword in query


    // ─── Method 4: Department filter with Pagination ──────────

    Page<EmployeeEntity> findByDepartment(String department, Pageable pageable);
    // Spring generates:
    // SELECT * FROM employees WHERE department = ?
    // With pagination applied automatically
    // Use case: "Show me all Engineering employees, page 1"


    // ─── Method 5: Existence check by ID ─────────────────────
    // Already FREE from JpaRepository:
    // existsById(Long id) — use this in service layer
    // No need to redefine it here
}
