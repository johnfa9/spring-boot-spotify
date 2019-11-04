package com.example.springbootspotify.service;

import com.example.springbootspotify.models.UserRole;


public interface UserRoleService {

    public UserRole createRole(UserRole newRole);

    public UserRole getRole(String roleName);
}