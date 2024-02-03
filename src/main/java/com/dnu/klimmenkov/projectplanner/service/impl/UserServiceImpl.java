package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.User;
import com.dnu.klimmenkov.projectplanner.repository.UserRepository;
import com.dnu.klimmenkov.projectplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }
}
