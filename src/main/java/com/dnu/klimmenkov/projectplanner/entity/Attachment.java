package com.dnu.klimmenkov.projectplanner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @NotBlank(message = "File name cannot be blank!")
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @NotBlank(message = "File type cannot be blank!")
    @Column(name = "file_type", length = 50, nullable = false)
    private String fileType;

    @NotBlank(message = "File path cannot be blank!")
    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
}
