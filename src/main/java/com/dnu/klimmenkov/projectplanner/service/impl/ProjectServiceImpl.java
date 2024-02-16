package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.Project;
import com.dnu.klimmenkov.projectplanner.repository.ProjectRepository;
import com.dnu.klimmenkov.projectplanner.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> allProjects = projectRepository.findAll();

        return allProjects;

    }

    @Override
    public void deleteProject(int id) {
        projectRepository.deleteById((long) id);
    }
}
