package com.dnu.klimmenkov.projectplanner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login", length = 50, unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    @Column(name = "roles", length = 20, nullable = false)
    private String roles;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
