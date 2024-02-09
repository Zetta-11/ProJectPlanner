package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final ProjectService projectService;
    private final UserService userService;

    public RegistrationController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("allProjects", projectService.getAllProjects());
        model.addAttribute(new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(Model model,
                               @RequestParam String confirmPassword,
                               @Valid @ModelAttribute("user") User user,
                               BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("allProjects", projectService.getAllProjects());
            return "registration";
        }

        User existingUser = userService.findByLogin(user.getLogin());
        if (existingUser != null) {
            model.addAttribute("userExistsError", true);
            model.addAttribute("allProjects", projectService.getAllProjects());
            return "registration";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            model.addAttribute("allProjects", projectService.getAllProjects());
            return "registration";
        }

        userService.saveUser(user);
        return "redirect:/home";
    }

}
