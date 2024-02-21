package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@AllArgsConstructor
public class HomeController {

    final private UserService userService;

    final private ProjectService projectService;

    @GetMapping("/home")
    public String getHomePage() {
        return "home/index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "home/about";
    }

    @GetMapping("/projects")
    public String getProjectsPage(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "home/allProjects";
    }

    @GetMapping("/projects/{projectId}")
    public String showProjectDetails(@PathVariable("projectId") int projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            return "home/allProjects";
        }
        int userCount = projectService.countUsersByProjectId(projectId);
        model.addAttribute("project", project);
        model.addAttribute("userCount", userCount);
        return "home/projectDetails";
    }

    //TODO TEST METHOD REMOVE WHEN PROJECT IS DONE
    @PostMapping("/new-user")
    @ResponseBody
    public String createNewUser(@RequestBody User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userService.saveUser(user);
        return "User is saved!";
    }
}
