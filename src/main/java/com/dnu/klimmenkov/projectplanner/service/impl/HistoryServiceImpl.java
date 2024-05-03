package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.History;
import com.dnu.klimmenkov.projectplanner.repository.HistoryRepository;
import com.dnu.klimmenkov.projectplanner.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public void saveHistory(History history) {
        if (history.getActionDescription().contains("get") && history.getActionDescription().contains("ByProjectId")) {
            history.setActionDescription("getAllTasksForTasksBoard");
        }

        historyRepository.save(history);
    }

    @Override
    public List<History> getAllHistory() {
        List<History> histories = historyRepository.findAll();
        return histories;
    }

    @Override
    public List<History> filterHistory(String fromDate, String toDate, String user, String actionType) {
        List<History> histories = getAllHistory();

        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
            LocalDate startDate = LocalDate.parse(fromDate);
            LocalDate endDate = LocalDate.parse(toDate).plusDays(1);

            histories = histories.stream()
                    .filter(history -> {
                        LocalDate historyDate = LocalDate.from(history.getCreatedAt().toLocalDateTime());
                        return !historyDate.isBefore(startDate) && !historyDate.isAfter(endDate);
                    })
                    .collect(Collectors.toList());
        }

        if (user != null && !user.isEmpty()) {
            long userId = Long.parseLong(user);
            histories = histories.stream()
                    .filter(history -> history.getUser() != null && history.getUser().getId() == userId)
                    .collect(Collectors.toList());
        }

        if (actionType != null && !actionType.isEmpty()) {
            histories = histories.stream()
                    .filter(history -> history.getActionDescription().contains(actionType))
                    .collect(Collectors.toList());
        }

        return histories;
    }

    @Override
    public void deleteHistory(int id) {
        historyRepository.deleteById((long) id);
    }

    @Override
    public void clearAllHistory() {
        historyRepository.deleteAll();
    }
}
