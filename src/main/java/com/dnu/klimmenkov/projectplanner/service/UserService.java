package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(int id, User user);

    boolean checkPasswordIsValid(String password);

    int countTasksAssignedToUser(String login);

    int countTasksCreatedByUser(String login);

    int countTasksDoneByUser(String login);

    List<User> getAllUsers();

    List<User> getAllUsersByProjectId(int id);

    User findByLogin(String login);

    User getUserById(int id);

    void deleteUser(int id);
}
