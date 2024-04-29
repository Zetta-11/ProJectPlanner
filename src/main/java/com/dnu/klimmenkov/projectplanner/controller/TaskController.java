package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.Comment;
import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.CommentService;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final CommentService commentService;

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

    @GetMapping("/{taskId}")
    public String getTaskDetailsPage(@PathVariable("taskId") int taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return "redirect:/tasks";
        }
        model.addAttribute("task", task);
        model.addAttribute("comments", task.getComments());

        return "home/taskDetails";
    }

    @PostMapping("/updateStatus/{taskId}")
    public String updateTaskStatus(@PathVariable int taskId, @RequestParam String status) {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return "redirect:/tasks";
        }
        task.setStatus(status);
        taskService.saveTask(task);

        return "redirect:/tasks/{taskId}";
    }


    @GetMapping("/new")
    public String getTasksCreatePage(Model model) {
        List<User> users = userService.getAllUsers();
        List<Project> projects = projectService.getAllProjects();

        model.addAttribute("task", new Task());
        model.addAttribute("users", users);
        model.addAttribute("projects", projects);

        return "home/addTask";
    }

    @GetMapping("/usersByProjectId/{id}")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsersByProjectId(@PathVariable int id) {
        List<User> users = userService.getAllUsersByProjectId(id);
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public String createNewTask(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String deadLine,
                                RedirectAttributes redirectAttributes,
                                @Valid @ModelAttribute("task") Task task,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<User> users = userService.getAllUsers();
            List<Project> projects = projectService.getAllProjects();
            model.addAttribute("users", users);
            model.addAttribute("projects", projects);

            return "home/addTask";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDeadline = LocalDate.parse(deadLine, formatter);
            LocalDateTime localDateTime = parsedDeadline.atStartOfDay().withHour(20);
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Europe/Kiev"));
            Timestamp deadlineTimestamp = Timestamp.valueOf(zonedDateTime.toLocalDateTime());
            task.setDeadline(deadlineTimestamp);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Select deadline!");
            List<User> users = userService.getAllUsers();
            List<Project> projects = projectService.getAllProjects();
            model.addAttribute("users", users);
            model.addAttribute("projects", projects);

            return "redirect:/tasks/new";
        }

        task.setCreatedByUser(userService.findByLogin(userDetails.getUsername()));
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        task.setStatus("To Do");
        taskService.saveTask(task);

        return "redirect:/tasks";
    }

    @PostMapping("/addComment/{taskId}")
    public String addNewCommentToTheTask(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int taskId, @RequestParam String commentText) {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return "redirect:/tasks";
        }
        Comment comment = Comment.builder()
                .task(task)
                .commentText(commentText)
                .user(userService.findByLogin(userDetails.getUsername()))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        commentService.saveComment(comment);

        return "redirect:/tasks/{taskId}";
    }


    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") int taskId) {
        taskService.deleteTaskById(taskId);

        return "redirect:/tasks";
    }
}
