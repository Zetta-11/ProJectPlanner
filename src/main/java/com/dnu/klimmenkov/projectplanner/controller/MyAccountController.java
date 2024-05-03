package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/myAccount")
public class MyAccountController {

    private final UserService userService;

    @GetMapping
    public String showMyAccount(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String login = userDetails.getUsername();
        User user = userService.findUserByLogin(login);
        int tasksAssignedToUser = userService.countTasksAssignedToUser(login);
        int tasksCreatedByUser = userService.countTasksCreatedByUser(login);
        int tasksDoneByUser = userService.countTasksDoneByUser(login);

        model.addAttribute("tasksAssignedToUser", tasksAssignedToUser);
        model.addAttribute("tasksCreatedByUser", tasksCreatedByUser);
        model.addAttribute("tasksDoneByUser", tasksDoneByUser);
        model.addAttribute("user", user);

        return "account/myAccount";
    }

    @GetMapping("/changePassword")
    public String showChangePasswordForm() {
        return "account/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                 @RequestParam("password") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 RedirectAttributes redirectAttributes) {
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/myAccount/changePassword";
        }

        if (!userService.checkPasswordIsValid(newPassword)) {
            redirectAttributes.addFlashAttribute("error", "Password must be at least 8 characters " +
                    "long, contain at least one digit, one lowercase letter, " +
                    "one uppercase letter, and one special character.");
            return "redirect:/myAccount/changePassword";
        }

        String login = userDetails.getUsername();
        User user = userService.findUserByLogin(login);
        user.setPassword(newPassword);
        userService.saveUser(user);

        return "redirect:/myAccount";
    }
}
