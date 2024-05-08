package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.TaskHistory;

import java.util.List;

public interface TaskHistoryService {

    void saveTaskHistory(TaskHistory taskHistory);

    List<TaskHistory> getHistoriesByTaskId(int id);
}
