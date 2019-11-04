package com.example.springbootspotify.service;

import com.example.springbootspotify.models.UserProfile;

public class UserProfileServiceStub implements UserProfileService {

    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("batman@superhero.com");

        return userProfile;
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return null;
    }
}
