package com.example.springbootspotify.service;

import com.example.springbootspotify.config.JwtUtil;
import com.example.springbootspotify.models.User;
import com.example.springbootspotify.models.UserRole;
import com.example.springbootspotify.repositories.SongRepository;
import com.example.springbootspotify.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;



@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    private SongRepository songRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private User user;

    @InjectMocks
    private UserRole userRole;

    @Before
    public void initializeDummyUser() {
        user.setUsername("batman");
        user.setPassword("robin");
        user.setId(1L);
        user.setUsername("user1");
        user.setPassword("pw1");
        user.setUserRole(userRole);
        //user.setSongs(song);
    }

    @Test
    public void login_ValidUser_Success() {
        String encodedPassword = "123";
        String generatedToken = "456";

        when(userRepository.findByUsername(any())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(any())).thenReturn(encodedPassword);
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(jwtUtil.generateToken(any())).thenReturn(generatedToken);

        String token = userService.login(user);
        assertThat(token).isNotNull();
        assertThat(token).isEqualTo(generatedToken);

    }
}


//    @Test
//    public void getUser_ReturnsUser_Success(){
//
//        when(userRepository.findByUsername(anyString())).thenReturn(user);
//
//        User tempUser = userService.getUser(user.getUsername());
//
//        assertEquals(tempUser.getUsername(), user.getUsername());
//    }

//    @Test
//    public void login_UserNotFound_Error(){
//
//        when(userRepository.findByUsername(anyString())).thenReturn(null);
//
//        String token = userService.login(user);
//
//        assertEquals(token, null);
//    }

//    @Test
//    public void login1_UserFound_Success(){
//        String expectedToken = "12345";
//        when(userRepository.findByUsername(anyString())).thenReturn(user);
//        when(bCryptPasswordEncoder.matches(any(),any())).thenReturn(true);
//        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
//
//        String token = userService.login(user);
//
//        assertEquals(expectedToken, token);
//    }



//    @Test
//    public void login_UserFound_Success() {
//        String expectedToken = "12345";
//
//        userRole.setName("ROLE_ADMIN");
//
//        user.setId(1L);
//        user.setUsername("batman");
//        user.setPassword("robin");
//        user.setUserRole(userRole);
//
//        when(userRepository.findByUsername(anyString())).thenReturn(user);
//
//        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
//        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");
//
//        String actualToken = userService.createUser(user);
//
//        assertEquals(expectedToken, actualToken);
//    }

//    @Test
//    public void createUser_UserFound_Succes(){
//
//        when(userRepository.findByUsername(anyString())).thenReturn(null);
//
//        String token = userService.login(user);
//
//        assertEquals(token, null);
//    }
//}