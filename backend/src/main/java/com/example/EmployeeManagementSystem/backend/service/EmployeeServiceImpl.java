package com.example.EmployeeManagementSystem.backend.service;

import com.example.EmployeeManagementSystem.backend.constants.AppConstants;
import com.example.EmployeeManagementSystem.backend.dto.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.backend.dto.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.backend.entity.EmployeeEntity;
import com.example.EmployeeManagementSystem.backend.exception.DuplicateEmailException;
import com.example.EmployeeManagementSystem.backend.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.backend.mapper.EmployeeMapper;
import com.example.EmployeeManagementSystem.backend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    // ─── Dependencies ─────────────────────────────────────────
    // final = must be set via constructor (immutable after creation)
    // @RequiredArgsConstructor generates the constructor automatically
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;


    // ─── CREATE ───────────────────────────────────────────────
    @Override
    @Transactional          // write operation — full transaction
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDto) {

        // Step 1: Business Rule — check duplicate email
        if (employeeRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException(
                    "Employee already exists with email: "
                            + requestDto.getEmail()
            );
        }

        // Step 2: Convert RequestDto → Entity
        EmployeeEntity employee = employeeMapper.toEntity(requestDto);

        // Step 3: Save to database
        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        // Step 4: Convert saved Entity → ResponseDto and return
        return employeeMapper.toResponseDto(savedEmployee);
    }


    // ─── READ (single) ────────────────────────────────────────
    @Override
    @Transactional(readOnly = true)   // read operation — optimized
    public EmployeeResponseDTO getEmployeeById(Long id) {

        // findById returns Optional<Employee>
        // orElseThrow: if empty → throw our custom exception
        EmployeeEntity employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AppConstants.EMPLOYEE_NOT_FOUND + id
                ));

        return employeeMapper.toResponseDto(employee);
    }


    // ─── READ (all with pagination + sorting) ─────────────────
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeResponseDTO> getAllEmployees(
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir
    ) {
        // Step 1: Build Sort direction
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Step 2: Build Pageable object
        // PageRequest.of(pageNumber, pageSize, sort)
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Step 3: Fetch Page<Employee> from repository
        Page<EmployeeEntity> employeePage =
                employeeRepository.findAll(pageable);

        // Step 4: Convert Page<Employee> → Page<EmployeeResponseDto>
        // .map() transforms each element inside the Page
        return employeePage.map(employeeMapper::toResponseDto);
    }


    // ─── READ (search with pagination) ────────────────────────
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeResponseDTO> searchEmployees(
            String keyword,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

      //  Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Pageable pageable = PageRequest.of(pageNumber , pageSize , sort );

        // Uses our custom @Query from repository
        Page<EmployeeEntity> employeePage =
                employeeRepository.searchEmployees(keyword, pageable);

        return employeePage.map(employeeMapper::toResponseDto);
    }


    // ─── UPDATE ───────────────────────────────────────────────
    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(
            Long id,
            EmployeeRequestDTO requestDto
    ) {
        // Step 1: Check employee exists — throws exception if not
        EmployeeEntity existingEmployee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        AppConstants.EMPLOYEE_NOT_FOUND + id
                ));

        // Step 2: Business Rule — if email changed, check not duplicate
        boolean emailChanged = !existingEmployee
                .getEmail()
                .equalsIgnoreCase(requestDto.getEmail());

        if (emailChanged &&
                employeeRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException(
                    "Email already in use: " + requestDto.getEmail()
            );
        }

        // Step 3: Update fields on existing entity (NOT create new one)
        employeeMapper.updateEntityFromDto(requestDto, existingEmployee);

        // Step 4: Save updated entity
        // Hibernate detects changes (dirty checking) and runs UPDATE SQL
        EmployeeEntity updatedEmployee =
                employeeRepository.save(existingEmployee);

        // Step 5: Return updated data as ResponseDto
        return employeeMapper.toResponseDto(updatedEmployee);
    }


    // ─── DELETE ───────────────────────────────────────────────
    @Override
    @Transactional
    public void deleteEmployee(Long id) {

        // Step 1: Check employee exists before deleting
        // Why? deleteById() does NOT throw exception if id not found
        // Without this check: silent failure — user thinks it worked
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    AppConstants.EMPLOYEE_NOT_FOUND + id
            );
        }

        // Step 2: Delete
        employeeRepository.deleteById(id);
    }
}
