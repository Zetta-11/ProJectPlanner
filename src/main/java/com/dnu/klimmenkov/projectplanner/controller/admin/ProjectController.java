package com.dnu.klimmenkov.projectplanner.controller.admin;

import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin/allProjects";
    }



    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }
}
