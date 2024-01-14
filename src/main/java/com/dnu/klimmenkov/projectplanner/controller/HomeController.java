package com.dnu.klimmenkov.projectplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home/index";
    }

    @GetMapping("/about")
    public String unsecured() {
        return "home/about";
    }
}
