package com.example.springbootspotify.controller;

import com.example.springbootspotify.models.UserProfile;
import com.example.springbootspotify.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {


// replaced below
//    @Autowired
//    UserProfileService userProfileService;

    private UserProfileService userProfileService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }



    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }

    @PostMapping("/{username}")
    public UserProfile createUserProfile(@PathVariable String username, @RequestBody UserProfile userProfile) {
        return userProfileService.createUserProfile(username, userProfile);
    }
}