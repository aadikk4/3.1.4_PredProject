package com.example31._PredProject.service;

import com.example31._PredProject.model.Role;
import com.example31._PredProject.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    public Role findByName(String n) {
        return roleRepository.findByName(n);
    }
}