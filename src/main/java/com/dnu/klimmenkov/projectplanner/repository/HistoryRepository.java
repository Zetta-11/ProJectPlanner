package com.dnu.klimmenkov.projectplanner.repository;

import com.dnu.klimmenkov.projectplanner.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
