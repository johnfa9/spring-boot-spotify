package com.example.springbootspotify.service;

import com.example.springbootspotify.models.User;
import com.example.springbootspotify.models.UserProfile;
import com.example.springbootspotify.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Autowired   //added for profile
    public UserProfileServiceImpl(UserService userService, UserProfileRepository userProfileRepository){
        this.userService = userService;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) {
        User user = userService.getUser(username);
        newProfile.setUser(user);
        return userProfileRepository.save(newProfile);
        //user.setUserProfile(newProfile);
        //return userService.createUser(user).getUserProfile();
    }

    @Override
    public UserProfile getUserProfile(String username) {

        return userProfileRepository.findProfileByUsername(username);
    }
}