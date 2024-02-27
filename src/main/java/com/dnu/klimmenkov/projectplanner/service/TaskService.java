package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    List<Task> getAllTasksByProjectId(int id);
    List<Task> getToDoTasksByProjectId(int id);

    List<Task> getInProgressTasksByProjectId(int id);

    List<Task> getInDoneTasksByProjectId(int id);

    Task getTaskById(int id);

    void saveTask(Task task);

    void deleteTaskById(int id);
}
