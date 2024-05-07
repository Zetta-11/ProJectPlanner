package com.dnu.klimmenkov.projectplanner.controller.admin;

import com.dnu.klimmenkov.projectplanner.entity.History;
import com.dnu.klimmenkov.projectplanner.service.HistoryService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/history")
public class AdminHistoryController {

    private final HistoryService historyService;
    private final UserService userService;

    @GetMapping
    public String getAllHistoryLogs(
            @RequestParam(name = "fromDate", required = false) String fromDate,
            @RequestParam(name = "toDate", required = false) String toDate,
            @RequestParam(name = "user", required = false) String user,
            @RequestParam(name = "actionType", required = false) String actionType,
            Model model) {
        List<History> filteredHistory = historyService.filterHistory(fromDate, toDate, user, actionType);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("logs", filteredHistory);
        return "admin/allHistoryLogs";
    }
}
