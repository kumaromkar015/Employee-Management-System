package com.example.EmployeeManagementSystem.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EmployeeRecord")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String jobTitle;
	private String department;
	private double salary;


	@CreationTimestamp
	@Column(name = "created_at",
			nullable = false,
			updatable = false
	)
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updateAt;
}
