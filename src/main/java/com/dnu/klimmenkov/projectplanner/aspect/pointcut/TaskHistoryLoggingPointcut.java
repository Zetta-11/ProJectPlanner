package com.dnu.klimmenkov.projectplanner.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class TaskHistoryLoggingPointcut {

    @Pointcut("execution (* com.dnu.klimmenkov.projectplanner.controller.TaskController.addNewCommentToTheTask(..)) ")
    public void addNewComment() {
    }

    @Pointcut("execution (* com.dnu.klimmenkov.projectplanner.controller.TaskController.updateTaskStatus(..)) ")
    public void updateTaskStatus() {
    }

    @Pointcut("addNewComment() || updateTaskStatus()")
    public void allTaskHistoryMethods() {
    }
}
