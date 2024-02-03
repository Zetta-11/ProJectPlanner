package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    final ProjectService projectService;
    final UserService userService;

    public RegistrationController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("allProjects", projectService.getAllProjects());
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, @ModelAttribute("confirmPassword") String confirmPassword) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allProjects", projectService.getAllProjects());
            return "registration";
        }

        User existingUser = userService.findByLogin(user.getLogin());
        if (existingUser != null) {
            model.addAttribute("userExistsError", true);
            return "registration";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            return "registration";
        }

        userService.saveUser(user);
        return "redirect:/home";
    }

}
