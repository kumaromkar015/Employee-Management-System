# Employee-Management-System

# 🏢 Employee Management System

A full-stack web application built with **React** (frontend) and **Spring Boot + JPA** (backend) that enables organizations to efficiently manage employee records. It supports full CRUD operations, a responsive React UI, RESTful API design, DTO-based data transfer, mapper layer, security configuration, and JPA/Hibernate for seamless MySQL interaction.

---

## 📌 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)
- [Screenshots](#screenshots)
- [Author](#author)

---

## 📖 About the Project

The **Employee Management System** is a full-stack application where users can add, view, update, and delete employee records through a React UI connected to a Spring Boot REST backend. The backend follows a clean, professional architecture with dedicated layers for config, controller, DTO, entity, exception, mapper, repository, security, and service — running on **JDK 21**.

This project demonstrates:
- Full-stack integration between React and Spring Boot
- DTO pattern with a dedicated `EmployeeMapper` for entity ↔ DTO conversion
- Spring Data JPA + Hibernate for ORM-based database operations
- Security configuration via `EmployeeSecurity`
- Centralized exception handling with `GlobalExceptionHandler`
- Layered backend architecture following industry best practices

---

## ✨ Features

- ✅ Add new employee records via React form
- ✅ View all employees in a responsive list/table
- ✅ Update employee information
- ✅ Delete employee records
- ✅ DTO layer to decouple API contracts from database entities
- ✅ Mapper layer for clean entity ↔ DTO conversion
- ✅ Security configuration for API protection
- ✅ Centralized exception handling with meaningful HTTP responses
- ✅ Axios-based frontend-backend communication
- ✅ JPA/Hibernate auto-manages the database schema

---

## 🛠 Tech Stack

### Frontend
| Layer | Technology |
|---|---|
| UI Library | React.js |
| HTTP Client | Axios |
| Styling | CSS / Bootstrap |
| Routing | React Router DOM |

### Backend
| Layer | Technology |
|---|---|
| Language | Java (JDK 21) |
| Framework | Spring Boot |
| ORM | Spring Data JPA + Hibernate |
| Database | MySQL |
| Security | Spring Security (`EmployeeSecurity`) |
| Build Tool | Maven (Maven Wrapper included) |
| API Testing | Postman |
| Version Control | Git & GitHub |

---

## 🗂 Project Structure

```
EmployeeManagementSystem/
│
├── backend/
│   ├── src/main/java/com/example/EmployeeManagementSystem/backend/
│   │   │
│   │   ├── config/
│   │   │   └── EmployeeConfig.java              # App-level configuration (beans, settings)
│   │   │
│   │   ├── controller/
│   │   │   └── EmployeeController.java          # REST API endpoints (@RestController)
│   │   │
│   │   ├── dto/
│   │   │   └── EmployeeDTO.java                 # Data Transfer Object for API request/response
│   │   │
│   │   ├── entity/
│   │   │   └── EmployeeEntity.java              # JPA @Entity mapped to MySQL table
│   │   │
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java      # @ControllerAdvice — centralized error handling
│   │   │
│   │   ├── mapper/
│   │   │   └── EmployeeMapper.java              # Converts EmployeeEntity ↔ EmployeeDTO
│   │   │
│   │   ├── repository/
│   │   │   └── EmployeeRepository.java          # Extends JpaRepository<EmployeeEntity, Long>
│   │   │
│   │   ├── security/
│   │   │   └── EmployeeSecurity.java            # Spring Security configuration
│   │   │
│   │   ├── service/
│   │   │   └── EmployeeService.java             # Business logic (service layer)
│   │   │
│   │   └── BackendApplication.java              # Main Spring Boot entry point
│   │
│   └── src/main/resources/
│       ├── static/                              # Static assets (if any)
│       ├── templates/                           # Templates (if any)
│       └── application.properties              # DB config, JPA, security settings
│
├── frontend/                                    # React application
│   └── src/
│       ├── components/
│       │   ├── EmployeeList.jsx
│       │   ├── AddEmployee.jsx
│       │   ├── UpdateEmployee.jsx
│       │   └── Header.jsx
│       ├── services/
│       │   └── EmployeeService.js               # Axios API calls
│       ├── App.jsx
│       └── main.jsx
│
├── mvnw                                         # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                                     # Maven Wrapper (Windows)
├── pom.xml                                      # Maven dependencies
└── HELP.md
```

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 21
- Node.js 16+ and npm
- MySQL 8.0+
- Git

> Maven Wrapper (`mvnw` / `mvnw.cmd`) is included — no need to install Maven separately.

---

### Backend Setup

1. **Clone the repository**

```bash
git clone https://github.com/kumaromkar015/Employee-Management-System.git
cd Employee-Management-System
```

2. **Create the MySQL database**

```sql
CREATE DATABASE employee_db;
```

3. **Configure `src/main/resources/application.properties`**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
```

4. **Run the backend**

```bash
# Linux / Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Backend runs at: `http://localhost:8080`

---

### Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs at: `http://localhost:3000`

> Ensure the backend is running before starting the frontend.

---

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/employees`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/employees` | Fetch all employees |
| `GET` | `/api/employees/{id}` | Fetch a single employee by ID |
| `POST` | `/api/employees` | Add a new employee |
| `PUT` | `/api/employees/{id}` | Update an existing employee |
| `DELETE` | `/api/employees/{id}` | Delete an employee |

### Sample Request — Add Employee

**POST** `/api/employees`

```json
{
  "firstName": "Omkar",
  "lastName": "Sandilay",
  "email": "omkar@example.com",
  "department": "Engineering",
  "salary": 450000
}
```

### Sample Response — `200 OK`

```json
{
  "id": 1,
  "firstName": "Omkar",
  "lastName": "Sandilay",
  "email": "omkar@example.com",
  "department": "Engineering",
  "salary": 450000
}
```

> The API works with `EmployeeDTO` — the `EmployeeMapper` handles conversion to/from `EmployeeEntity` internally, keeping the database layer fully decoupled from the API contract.

---

## ⚠️ Exception Handling

`GlobalExceptionHandler` (`@ControllerAdvice`) catches exceptions across all controllers and returns structured JSON error responses:

| Scenario | HTTP Status | Message |
|---|---|---|
| Employee not found | `404 Not Found` | `Employee not found with id: {id}` |
| Invalid request body | `400 Bad Request` | Field validation error details |
| Unauthorized access | `401 Unauthorized` | Security restriction |
| Server error | `500 Internal Server Error` | Generic error message |

---

## 📸 Screenshots

> _Add screenshots of your React UI here._

| Employee List | Add Employee | Edit Employee |
|---|---|---|
| ![list](screenshots/list.png) | ![add](screenshots/add.png) | ![edit](screenshots/edit.png) |

---

## 👤 Author

**Omkar Kumar Sandilay**

- 📧 Email: [kumaromkar015@gmail.com](mailto:kumaromkar015@gmail.com)
- 💼 LinkedIn: [linkedin.com/in/omkarshandilya42](https://linkedin.com/in/omkarshandilya42/)
- 🐙 GitHub: [github.com/kumaromkar015](https://github.com/kumaromkar015/)
- 🌐 Portfolio: [about-omkar625.lovable.app](https://about-omkar625.lovable.app)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

> Built with ☕ Java 21 + Spring Boot + React by Omkar Kumar Sandilay
