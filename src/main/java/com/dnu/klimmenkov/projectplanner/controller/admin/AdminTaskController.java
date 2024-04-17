package com.dnu.klimmenkov.projectplanner.controller.admin;

import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/tasks")
public class AdminTaskController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    @GetMapping()
    public String adminTasksPage(Model model, @RequestParam(required = false) String priority,
                                 @RequestParam(required = false) String status,
                                 @RequestParam(required = false) String project,
                                 @RequestParam(required = false) String assignedTo) {
        List<Task> tasks = taskService.filterTasks(priority, status, project, assignedTo);
        model.addAttribute("tasks", tasks);
       // model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("users", userService.getAllUsers());

        return "admin/allTasks";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") int taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/admin/tasks";
    }
}
