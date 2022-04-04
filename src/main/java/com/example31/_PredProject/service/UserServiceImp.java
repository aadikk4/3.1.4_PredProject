package com.example31._PredProject.service;


import com.example31._PredProject.model.Role;
import com.example31._PredProject.model.User;
import com.example31._PredProject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private  PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private RoleService roleServiceImp;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void save(User user, String roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String[] names = roles.split("[\\s,]");
        List<Role> roleList = new ArrayList<>();
        for (String n : names) {
            Role role = new Role(n);
            if (roleServiceImp.existsByName(n)) {
                role = roleServiceImp.findByName(n);
            } else {
                roleServiceImp.save(role);
            }
            roleList.add(role);
        }
        userRepository.save(user);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}