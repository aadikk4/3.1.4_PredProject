package com.example31._PredProject.service;

import com.example31._PredProject.model.User;

public interface UserService {
    Iterable<User> allUsers();
    User findUserById(long id);
    void deleteById(long id);
    void save(User user, String roles);
    void saveUser(User user);
}
