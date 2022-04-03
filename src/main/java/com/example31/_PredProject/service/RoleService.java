package com.example31._PredProject.service;

import com.example31._PredProject.model.Role;

public interface RoleService {
    void save(Role role);
    boolean existsByName(String name);
    Role findByName(String n);
}
