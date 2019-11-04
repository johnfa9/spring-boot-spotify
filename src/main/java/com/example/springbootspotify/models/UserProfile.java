package com.example.springbootspotify.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @Column (name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private String address;

    public UserProfile() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    @OneToOne(mappedBy = "userProfile", cascade={CascadeType.DETACH,  //mapped=>biwire
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }
}
