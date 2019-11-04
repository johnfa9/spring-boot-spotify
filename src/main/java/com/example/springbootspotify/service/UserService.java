package com.example.springbootspotify.service;

import com.example.springbootspotify.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
        //in Spring Security to talk to database
        public Iterable<User> listUsers();
       // public User createUser(User newUser);
        //public User login(String username, String password); //removed for profile stub
        public String login(User user);  //security setting
        public String createUser(User newUser);  //security setting
       // public void deleteById(Long userId);
        public HttpStatus deleteById(Long userId);
        public User getUser(String username);  //created for profile
        public User addSong(String username, int songId); //created for song
        public User deleteSong(String username, int songId);
}
