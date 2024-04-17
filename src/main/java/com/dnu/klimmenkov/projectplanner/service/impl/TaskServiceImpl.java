package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.repository.TaskRepository;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        return allTasks;
    }

    @Override
    public List<Task> getAllTasksByProjectId(int id) {
        List<Task> tasksByProjectId = taskRepository.findTaskByProjectId(id);
        return tasksByProjectId;
    }

    @Override
    public List<Task> getToDoTasksByProjectId(int id) {
        List<Task> allTasksByProjectId = getAllTasksByProjectId(id);
        List<Task> toDoTasks = allTasksByProjectId.stream().filter(t -> t.getStatus().equals("To Do")).toList();

        return toDoTasks;
    }

    @Override
    public List<Task> getInProgressTasksByProjectId(int id) {
        List<Task> allTasksByProjectId = getAllTasksByProjectId(id);
        List<Task> inProgressTasks = allTasksByProjectId.stream().filter(t -> t.getStatus().equals("In Progress")).toList();

        return inProgressTasks;
    }

    @Override
    public List<Task> getInDoneTasksByProjectId(int id) {
        List<Task> allTasksByProjectId = getAllTasksByProjectId(id);
        List<Task> DoneTasks = allTasksByProjectId.stream().filter(t -> t.getStatus().equals("Done")).toList();

        return DoneTasks;
    }

    @Override
    public List<Task> filterTasks(String priority, String status, String project, String assignedTo) {
        List<Task> tasks = this.getAllTasks();

        if (priority != null && !priority.isEmpty()) {
            String[] priorityRange = priority.split("-");
            int minPriority = Integer.parseInt(priorityRange[0]);
            int maxPriority = Integer.parseInt(priorityRange[1]);

            tasks = tasks.stream()
                    .filter(task -> task.getPriority() >= minPriority && task.getPriority() <= maxPriority)
                    .collect(Collectors.toList());
        }

        if (status != null && !status.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> task.getStatus().equals(status))
                    .collect(Collectors.toList());
        }

        if (project != null && !project.isEmpty()) {
            tasks = tasks.stream().filter(task -> task.getProject().getName().equals(project)).collect(Collectors.toList());
        }

        if (assignedTo != null && !assignedTo.isEmpty()) {
            tasks = tasks.stream().filter(task -> {
                if (task.getAssignedToUser() != null) {
                    return task.getAssignedToUser().getLogin().equals(assignedTo);
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
        }

        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.getReferenceById((long) id);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById((long) id);
    }
}
