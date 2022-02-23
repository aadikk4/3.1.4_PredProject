package com.example31._PredProject.service;

import com.example31._PredProject.model.User;
import java.util.List;

public interface UserService {
    List<User> show();
    User getUserById(long id);
    void addUser(User user);
    void removeUser(long id);
    void updateUser(User user);
}
