package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.TaskHistory;
import com.dnu.klimmenkov.projectplanner.repository.TaskHistoryRepository;
import com.dnu.klimmenkov.projectplanner.service.TaskHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskHistoryServiceImpl implements TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;

    @Override
    public void saveTaskHistory(TaskHistory taskHistory) {
        taskHistoryRepository.save(taskHistory);
    }

    @Override
    public List<TaskHistory> getHistoriesByTaskId(int id) {
        List<TaskHistory> taskHistories = taskHistoryRepository.findByTaskId((long) id);
        return taskHistories;
    }
}
