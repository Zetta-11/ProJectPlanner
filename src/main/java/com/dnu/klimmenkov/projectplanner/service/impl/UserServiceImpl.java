package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.repository.TaskRepository;
import com.dnu.klimmenkov.projectplanner.repository.UserRepository;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TaskRepository taskRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        User existingUser = userRepository.findById((long) id).orElse(null);
        if (existingUser != null) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setProject(updatedUser.getProject());
            existingUser.setRoles(updatedUser.getRoles());

            userRepository.save(existingUser);
        }
    }

    @Override
    public boolean checkPasswordIsValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    @Override
    public int countTasksAssignedToUser(String login) {
        return taskRepository.countTasksByAssignedToUserLoginAndStatusNotIn(login, Arrays.asList("Done", "In Progress"));
    }

    @Override
    public int countTasksCreatedByUser(String login) {
        return taskRepository.countTasksByCreatedByUserLogin(login);
    }

    @Override
    public int countTasksDoneByUser(String login) {
        return taskRepository.countTasksByAssignedToUserLoginAndStatus(login, "Done");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public List<User> getAllUsersByProjectId(int id) {
        List<User> allUsersByProjectId = userRepository.findAllByProjectId(id);
        return allUsersByProjectId;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById((long) id);
    }
}
