package com.example.springbootspotify.service;

import com.example.springbootspotify.models.UserProfile;

public interface UserProfileService {

    public UserProfile createUserProfile(String username, UserProfile newProfile);
    public UserProfile getUserProfile(String username);
}