package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.NotFoundException;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getAll() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, userService.getAll().size());
    }

    @Test
    public void getByUsername_noUser_throwException() {
        //when(userRepository.findByUsername(Mockito.any(String.class))).thenReturn(null);
        assertThrows(NotFoundException.class, () -> { userService.getByUsername("admin");});
    }

    @Test
    public void getByUsername_User_throwException() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setId((long) 1);
        user.setRoles(new HashSet<>());
        when(userRepository.findByUsername(Mockito.any(String.class))).thenReturn(Optional.of(user));
        User foundUser = userService.getByUsername("admin");
        assertEquals((long) 1, foundUser.getId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("testPassword", foundUser.getPassword());
        assertEquals(0, foundUser.getRoles().size());
    }
}