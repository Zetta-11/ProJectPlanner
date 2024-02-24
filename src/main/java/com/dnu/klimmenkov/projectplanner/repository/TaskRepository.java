package com.dnu.klimmenkov.projectplanner.repository;

import com.dnu.klimmenkov.projectplanner.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByProjectId(int id);

    int countTasksByAssignedToUserLoginAndStatusNotIn(String login, List<String> statuses);

    int countTasksByAssignedToUserLoginAndStatus(String login, String status);

    int countTasksByCreatedByUserLogin(String login);
}
