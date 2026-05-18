package com.example.EmployeeManagementSystem.backend.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.EmployeeManagementSystem.backend.constants.AppConstants;
import com.example.EmployeeManagementSystem.backend.dto.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.backend.dto.EmployeeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.EmployeeManagementSystem.backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController                         // = @Controller + @ResponseBody
@RequestMapping("/api/employees")       // base URL for all endpoints
@RequiredArgsConstructor                // constructor injection
@CrossOrigin(origins = "http://localhost:5173") // allow React frontend
public class EmployeeController {

    private final EmployeeService employeeService;


    // ─────────────────────────────────────────────────────────
    // 1. ADD EMPLOYEE
    // POST /api/employees
    // Body: EmployeeRequestDto (JSON)
    // ─────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDto
    ) {
        EmployeeResponseDTO savedEmployee =
                employeeService.addEmployee(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)          // 201
                .body(savedEmployee);
    }


    // ─────────────────────────────────────────────────────────
    // 2. GET EMPLOYEE BY ID
    // GET /api/employees/{id}
    // ─────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable Long id
    ) {
        EmployeeResponseDTO employee =
                employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employee);          // 200
    }


    // ─────────────────────────────────────────────────────────
    // 3. GET ALL EMPLOYEES (with Pagination + Sorting)
    // GET /api/employees
    // GET /api/employees?page=0&size=10&sortBy=firstName&sortDir=asc
    // ─────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER_STR)
            int page,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE_STR)
            int size,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY)
            String sortBy,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIR)
            String sortDir
    ) {
        Page<EmployeeResponseDTO> employeePage =
                employeeService.getAllEmployees(page, size, sortBy, sortDir);

        return ResponseEntity.ok(buildPageResponse(employeePage));
    }


    // ─────────────────────────────────────────────────────────
    // 4. SEARCH EMPLOYEES (with Pagination)
    // GET /api/employees/search?keyword=raj&page=0&size=10
    // ─────────────────────────────────────────────────────────
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchEmployees(
            @RequestParam String keyword,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER_STR)
            int page,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE_STR)
            int size,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY)
            String sortBy,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIR)
            String sortDir
    ) {
        Page<EmployeeResponseDTO> employeePage =
                employeeService.searchEmployees(
                        keyword, page, size, sortBy, sortDir
                );

        return ResponseEntity.ok(buildPageResponse(employeePage));
    }


    // ─────────────────────────────────────────────────────────
    // 5. UPDATE EMPLOYEE
    // PUT /api/employees/{id}
    // Body: EmployeeRequestDto (JSON)
    // ─────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDto
    ) {
        EmployeeResponseDTO updatedEmployee =
                employeeService.updateEmployee(id, requestDto);

        return ResponseEntity.ok(updatedEmployee);   // 200
    }


    // ─────────────────────────────────────────────────────────
    // 6. DELETE EMPLOYEE
    // DELETE /api/employees/{id}
    // ─────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(
            @PathVariable Long id
    ) {
        employeeService.deleteEmployee(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", AppConstants.EMPLOYEE_DELETED);

        return ResponseEntity.ok(response);          // 200
    }


    // ─────────────────────────────────────────────────────────
    // PRIVATE HELPER — Build consistent pagination response
    // Used by getAllEmployees and searchEmployees
    // ─────────────────────────────────────────────────────────
    private Map<String, Object> buildPageResponse(
            Page<EmployeeResponseDTO> page
    ) {
        Map<String, Object> response = new HashMap<>();

        response.put("employees", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        response.put("pageSize", page.getSize());
        response.put("isFirst", page.isFirst());
        response.put("isLast", page.isLast());
        response.put("hasNext", page.hasNext());
        response.put("hasPrevious", page.hasPrevious());

        return response;
    }

}
