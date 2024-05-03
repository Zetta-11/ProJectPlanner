package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.History;

import java.util.List;

public interface HistoryService {
    void saveHistory(History history);

    List<History> getAllHistory();

    void deleteHistory(int id);

    void clearAllHistory();
}
