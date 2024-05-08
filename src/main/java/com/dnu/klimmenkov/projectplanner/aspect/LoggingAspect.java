package com.dnu.klimmenkov.projectplanner.aspect;

import com.dnu.klimmenkov.projectplanner.entity.History;
import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.entity.TaskHistory;
import com.dnu.klimmenkov.projectplanner.service.HistoryService;
import com.dnu.klimmenkov.projectplanner.service.TaskHistoryService;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Aspect
@Component
@AllArgsConstructor
public class LoggingAspect {

    private final HistoryService historyService;
    private final TaskHistoryService taskHistoryService;
    private final UserService userService;
    private final TaskService taskService;

    @Around("com.dnu.klimmenkov.projectplanner.aspect.pointcut.LoggingPointcut.allShowAddDeleteMethods()")
    public Object logShowAddDeleteMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        String username = getUsernameFromAuthentication();

        saveHistoryEntry(username, methodName);

        return joinPoint.proceed();
    }

    @Around("com.dnu.klimmenkov.projectplanner.aspect.pointcut.TaskHistoryLoggingPointcut.allTaskHistoryMethods()")
    public Object logTaskHistoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();


        String username = getUsernameFromAuthentication();
        Integer taskId = getTaskIdFromMethodArguments(joinPoint);

        Task task = taskService.getTaskById(taskId);
        String oldStatus = task.getStatus();
        Object result = joinPoint.proceed();

        task = taskService.getTaskById(taskId);
        String newStatus = task.getStatus();

        String details = methodName + " was done by " + username;
        if (methodName.equals("updateTaskStatus")) {
            details = "Status updated from " + oldStatus + " to " + newStatus;
        }
        saveTaskHistoryEntry(username, methodName, taskId, details);

        return result;
    }


    private String getUsernameFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null) ? authentication.getName() : null;
    }

    private Integer getTaskIdFromMethodArguments(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Integer) {
                return (Integer) arg;
            }
        }
        throw new IllegalArgumentException("Task ID not found in method arguments");
    }

    private void saveHistoryEntry(String username, String methodName) {
        History history = History.builder()
                .user((username != null) ? userService.findUserByLogin(username) : null)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .actionDescription(methodName)
                .build();
        historyService.saveHistory(history);
    }

    private void saveTaskHistoryEntry(String username, String methodName, Integer taskId, String details) {
        TaskHistory taskHistory = TaskHistory.builder()
                .user((username != null) ? userService.findUserByLogin(username) : null)
                .task(taskService.getTaskById(taskId))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .action(methodName)
                .details(details)
                .build();
        taskHistoryService.saveTaskHistory(taskHistory);
    }

}
