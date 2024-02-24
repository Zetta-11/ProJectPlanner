package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

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

    @GetMapping("/new")
    public String getTasksCreatePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<User> users = userService.getAllUsers();
        List<Project> projects = projectService.getAllProjects();

        model.addAttribute("task", new Task());
        model.addAttribute("users", users);
        model.addAttribute("projects", projects);

        return "home/addTask";
    }

    @PostMapping()
    public String createNewTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "home/addTask";
        }

        // Add logic to save task
        return "redirect:/tasks";
    }
}
