package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.History;
import com.dnu.klimmenkov.projectplanner.repository.HistoryRepository;
import com.dnu.klimmenkov.projectplanner.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public void saveHistory(History history) {
        historyRepository.save(history);
    }

    @Override
    public List<History> getAllHistory() {
        List<History> histories = historyRepository.findAll();
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
