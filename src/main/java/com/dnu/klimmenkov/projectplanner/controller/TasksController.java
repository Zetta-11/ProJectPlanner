package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping()
    public String getTasksPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String currentUsername = userDetails.getUsername();
        Project currentProject = userService.findByLogin(currentUsername).getProject();
        int projectId = currentProject.getId();
        List<Task> tasks = taskService.getAllTasksByProjectId(projectId);
        List<Task> toDoTasks = taskService.getToDoTasksByProjectId(projectId);
        List<Task> inProgressTasks = taskService.getInProgressTasksByProjectId(projectId);
        List<Task> doneTasks = taskService.getInDoneTasksByProjectId(projectId);

        model.addAttribute("tasks", tasks);
        model.addAttribute("toDoTasks", toDoTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);
        model.addAttribute("doneTasks", doneTasks);

        return "home/tasks";
    }

}
