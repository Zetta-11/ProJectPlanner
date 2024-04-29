package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);

        return "redirect:/tasks";
    }
}
