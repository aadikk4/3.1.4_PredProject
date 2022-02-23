package com.example31._PredProject.service;


import com.example31._PredProject.dao.UserDao;
import com.example31._PredProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> show() {
        return userDao.show();
    }

    @Transactional
    @Override
    public User getUserById(long id) { return userDao.getUserById(id); }

    @Transactional
    @Override
    public void addUser(User user) { userDao.addUser(user); }

    @Transactional
    @Override
    public void removeUser(long id) { userDao.removeUser(id); }

    @Transactional
    @Override
    public void updateUser(User user) { userDao.updateUser(user); }
}
