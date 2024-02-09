package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(int id, User user);

    List<User> getAllUsers();

    User findByLogin(String login);

    User getUserById(int id);

    void deleteUser(int id);
}
