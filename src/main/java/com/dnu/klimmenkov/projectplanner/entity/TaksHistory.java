package com.dnu.klimmenkov.projectplanner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "task_history")
public class TaksHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "details", nullable = true)
    private String details;

    //TODO
}
