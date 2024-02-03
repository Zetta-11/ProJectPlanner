package com.dnu.klimmenkov.projectplanner.repository;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
