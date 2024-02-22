package com.dnu.klimmenkov.projectplanner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Task name cannot be blank!")
    @Column(name = "task_name", nullable = false)
    private String taskName;

    @NotBlank(message = "Task description cannot be blank!")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id")
    private User assignedToUser;

    @NotNull(message = "Cannot be null!")
    @Max(value = 10, message = "Should be from 0 to 10")
    private int priority;

    @NotNull(message = "Deadline cannot be null!")
    private Timestamp deadline;

    @Pattern(regexp = "^(Done|In Progress|To Do)$", message = "Status should be: Done, In Progress or To Do")
    private String status;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
}
