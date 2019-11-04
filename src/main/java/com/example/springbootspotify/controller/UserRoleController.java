package com.example.springbootspotify.controller;

import com.example.springbootspotify.models.UserRole;
import com.example.springbootspotify.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    @Autowired
    UserRoleService roleService;

    @GetMapping("/{rolename}")
    public UserRole getRole(@PathVariable String rolename) {
        return roleService.getRole(rolename);
    }

    @PostMapping
    public UserRole createRole(@RequestBody UserRole role) {
        return roleService.createRole(role);
    }

}