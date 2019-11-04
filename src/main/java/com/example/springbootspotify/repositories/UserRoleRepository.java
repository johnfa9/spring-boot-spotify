package com.example.springbootspotify.repositories;

import com.example.springbootspotify.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    public UserRole findByName(String name);
}