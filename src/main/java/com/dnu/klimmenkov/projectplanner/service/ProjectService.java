package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.Project;

import java.util.List;

public interface ProjectService {
    void saveProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(int id);

    void deleteProject(int id);
}
