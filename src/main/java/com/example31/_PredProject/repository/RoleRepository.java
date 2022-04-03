package com.example31._PredProject.repository;

import com.example31._PredProject.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface RoleRepository extends CrudRepository<Role, Long> {
    boolean existsByName(String name);
    Role findByName(String n);
}