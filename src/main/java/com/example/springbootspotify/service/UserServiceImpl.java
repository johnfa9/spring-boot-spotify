package com.example.springbootspotify.service;

import com.example.springbootspotify.config.JwtUtil;
import com.example.springbootspotify.models.Song;
import com.example.springbootspotify.models.User;
import com.example.springbootspotify.models.UserRole;
import com.example.springbootspotify.repositories.SongRepository;
import com.example.springbootspotify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

//    @Override
//    public String login(User user){
//        //security settings
//        if(userRepository.login(user.getUsername(), user.getPassword()) != null){
//            UserDetails userDetails = loadUserByUsername(user.getUsername());
//            return jwtUtil.generateToken(userDetails);
//        }
//        return null;
//    }

    @Override
    public String login(User user){
        User newUser = userRepository.findByUsername(user.getUsername());

        if(newUser != null && bCryptPasswordEncoder.matches(user.getPassword(), newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loadUserByUsername() is called by Spring Security by default to to check against the User data.
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, new ArrayList<>());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }

    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }//repository is autowired. findall is built in method of a repository

//    @Override
//    public User createUser(User newUser) {
//        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName()); //added for role
//        newUser.setUserRole(userRole); //added for role
//        return userRepository.save(newUser);
//    }

    @Override
    public String createUser(User newUser) {  //newUser = body
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

//    @Override  //removed for profile stub
//    public User login(String username, String password) {
//
//        return userRepository.login(username, password);
//    }

    @Override
    public HttpStatus deleteById(Long userId) {  //changed from void

        userRepository.deleteById(userId);
        return HttpStatus.OK;  //added w/http
    }

    @Override
    public User getUser(String username) {

        return userRepository.findByUsername(username);  //created for profile
    }

    //For songs
    @Autowired
    SongRepository songRepository;

    @Override
    public User addSong(String username, int courseId) {
        Song song = songRepository.findById(courseId).get();
        User user = getUser(username);
        user.addSong(song);
        return userRepository.save(user);
    }

    @Override
    public User deleteSong(String username, int songId) {
        Song song = songRepository.findById(songId).get();
        User user = getUser(username);
        user.getSongs().remove(song);
        return userRepository.save(user);
    }
}
