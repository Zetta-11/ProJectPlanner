package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home/index";
    }

    @GetMapping("/about")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String unsecured() {
        return "home/about";
    }

    //TEST METHOD REMOVE WHEN PROJECT IS DONE
    @PostMapping("/new-user")
    @ResponseBody
    public String createNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User is saved!";
    }
}
