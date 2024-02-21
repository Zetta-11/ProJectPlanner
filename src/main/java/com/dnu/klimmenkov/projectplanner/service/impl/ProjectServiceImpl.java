package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.repository.ProjectRepository;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public void saveProject(Project project) {
        project.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> allProjects = projectRepository.findAll();

        return allProjects;

    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.getReferenceById((long) id);
    }

    @Override
    public void deleteProject(int id) {
        projectRepository.deleteById((long) id);
    }

    @Override
    public int countUsersByProjectId(int projectId) {
        return projectRepository.countUsersByProjectId(projectId);
    }
}
