package com.dnu.klimmenkov.projectplanner.controller.admin;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.service.ProductService;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/projects")
public class ProjectController {

    private final ProjectService projectService;

    private final ProductService productService;

    @GetMapping
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin/allProjects";
    }

    @GetMapping("/new")
    public String getAddProjectPage(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("allProducts", productService.getAllProducts());
        return "admin/addProject";
    }

    @PostMapping()
    public String addProject(Model model, @Valid @ModelAttribute("project") Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allProducts", productService.getAllProducts());
            return "admin/addProject";
        }
        projectService.saveProject(project);
        return "redirect:/admin/projects";
    }

    @GetMapping("/update/{id}")
    public String showEditProjectForm(@PathVariable("id") int id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("allProducts", productService.getAllProducts());
        return "admin/addProject";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }
}
