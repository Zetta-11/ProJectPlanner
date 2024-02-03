package com.dnu.klimmenkov.projectplanner.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login", length = 50, unique = true, nullable = false)
    @NotBlank(message = "Login cannot be blank!")
    @Email(message = "Invalid email format")
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    private String password;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    @Column(name = "roles", length = 20, nullable = false)
    private String roles;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
