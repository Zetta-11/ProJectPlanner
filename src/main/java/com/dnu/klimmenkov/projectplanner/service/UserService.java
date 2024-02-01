package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();
}
