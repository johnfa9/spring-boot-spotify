package com.example.springbootspotify.controller;

import com.example.springbootspotify.config.JwtResponse;
import com.example.springbootspotify.models.Song;
import com.example.springbootspotify.models.User;
import com.example.springbootspotify.service.SongService;
import com.example.springbootspotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    @GetMapping("/hello")
    public String hello() {

        return "Hello World !!";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")   //security
    @GetMapping("/user/list")
    public Iterable<User> listUsers() {

        return userService.listUsers();
    }

//    @PostMapping("/signup")
//    public User createUser(@RequestBody User newUser) {
//
//        return userService.createUser(newUser);
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

//    @GetMapping("/login/{username}/{password}")
//    public User login(@PathVariable String username, @PathVariable String password) {
//        return userService.login(username, password);
//    }

    @DeleteMapping("/user/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {

        userService.deleteById(userId);
        //return HttpStatus.OK;
        return userService.deleteById(userId);  //added with http
    }
    //for songs
    @PutMapping("/user/{username}/{songId}")
    public User addSong(@PathVariable String username, @PathVariable int songId){
        return userService.addSong(username, songId);
    }

    //delete post by user
    @DeleteMapping("/user/{username}/{songId}")
    public HttpStatus deleteSongById(@PathVariable String username, @PathVariable Integer songId) {
        userService.deleteSong(username, songId);
        return HttpStatus.OK;
    }

}
