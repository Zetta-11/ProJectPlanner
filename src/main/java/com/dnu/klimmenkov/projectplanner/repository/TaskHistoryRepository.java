package com.dnu.klimmenkov.projectplanner.repository;

import com.dnu.klimmenkov.projectplanner.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    List<TaskHistory> findByTaskId(Long taskId);
}
