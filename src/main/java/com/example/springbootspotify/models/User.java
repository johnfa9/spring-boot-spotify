package com.example.springbootspotify.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(  //for role
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //for profile
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_profile_id") //foreign key column  name="user_profile_id"
    private UserProfile userProfile;

    //for role
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_song",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;

    public List<Song> getSongs(){ return songs; }

    public void setSongs(List<Song> songs) { this.songs = songs; }

    public UserRole getUserRole() {
        return userRole; }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole; }

    //Getter and Setter methods are important. Spring Boot uses them behind the scenes to initialize the object.
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {

        this.userProfile = userProfile;
    }

    //for adding a song for user
    public List<Song> addSong(Song song) {
        if (songs == null)
            songs = new ArrayList<>();
        songs.add(song);
        return songs;
    }
}
