package com.example.EmployeeManagementSystem.backend.constants;

public class AppConstants {
    private AppConstants() {}

    // ─── Pagination ───────────────────────────────────────────
    public static final int    DEFAULT_PAGE_NUMBER     = 0;
    public static final int    DEFAULT_PAGE_SIZE       = 10;
    public static final String DEFAULT_PAGE_NUMBER_STR = "0";
    public static final String DEFAULT_PAGE_SIZE_STR   = "10";
    public static final String DEFAULT_SORT_BY         = "firstName";
    public static final String DEFAULT_SORT_DIR        = "asc";

    // ─── Messages ─────────────────────────────────────────────
    public static final String EMPLOYEE_CREATED   = "Employee created successfully";
    public static final String EMPLOYEE_UPDATED   = "Employee updated successfully";
    public static final String EMPLOYEE_DELETED   = "Employee deleted successfully";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found with id: ";
    public static final String DUPLICATE_EMAIL    = "Employee already exists with email: ";
}
