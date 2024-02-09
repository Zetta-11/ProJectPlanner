package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ProjectService projectService;

    public AdminController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping()
    public String getMainAdminPage() {
        return "admin/adminMainPage";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/allUsers";
    }

    @GetMapping("/users/update/{id}")
    public String getUpdateUserForm(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin/updateUser";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(Model model, @PathVariable int id, @Valid @ModelAttribute("user") User updatedUser, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("user", updatedUser);
            model.addAttribute("projects", projectService.getAllProjects());
            return "admin/updateUser";
        }

        userService.updateUser(id, updatedUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
