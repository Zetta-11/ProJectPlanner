package com.dnu.klimmenkov.projectplanner.aspect;

import com.dnu.klimmenkov.projectplanner.entity.History;
import com.dnu.klimmenkov.projectplanner.service.HistoryService;
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
    private final UserService userService;

    @Around("com.dnu.klimmenkov.projectplanner.aspect.pointcut.LoggingPointcut.allShowAddDeleteMethods()")
    public Object afterReturningLoggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null) ? authentication.getName() : null;

        History history = History.builder()
                .user((username != null) ? userService.findUserByLogin(username) : null)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .actionDescription(methodName)
                .build();
        historyService.saveHistory(history);

        return joinPoint.proceed();
    }
}
