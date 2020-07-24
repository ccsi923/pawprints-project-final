package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.controller.UserController;
import com.ironhack.userservice.exceptions.NotFoundException;
import com.ironhack.userservice.model.Address;
import com.ironhack.userservice.model.Admin;
import com.ironhack.userservice.model.Client;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.model.dto.UserRequest;
import com.ironhack.userservice.service.UserService;
import com.ironhack.userservice.util.JwtUtil;
import org.apache.catalina.connector.ClientAbortException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerImplTest {
    @Autowired
    private UserController userController;
    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private JwtUtil jwtUtil;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String token, token2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        token = "Bearer " + jwtUtil.generateToken("test");
    }


    @Test
    public void connectionTry_NoTokenSent_Forbidden() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAll_NotUsers_EmptyList() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        String response = mockMvc.perform(get("/users").header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("[]", response);
    }

    @Test
    public void getAll_Users_List() throws Exception {
        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("testPassword");
        user1.setUserEmail("pepito@gma.com");
        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("testPassword2");
        user2.setUserEmail("pepito@gma.com");
        when(userService.getAll()).thenReturn(Stream.of(user1, user2).collect(Collectors.toList()));
        mockMvc.perform(get("/users").header("Authorization", token) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("test"))
                .andExpect(jsonPath("$[1].username").value("test2"));
    }

    @Test
    public void getByUsername_ValidUsername_FoundUser() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        when(userService.getByUsername("test") ).thenReturn(user);
        mockMvc.perform(get("/users/test").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(user.getUsername()))
                .andExpect(jsonPath("password").value(user.getPassword()));

    }

    @Test
    public void getByUsername_InvalidUsername_NotFound() throws Exception {
        when(userService.getByUsername(Mockito.anyString())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/users/admin").header("Authorization", token)).andExpect(status().isNotFound());
    }

    @Test
    public void createClient() throws Exception {
        mockMvc.perform(post("/client")
                .header("Authorization", token)
                .content(objectMapper.writeValueAsString(new UserRequest("cristia@gmail.com", "superCris", "superCris",
                        new Address(), new Address("tik tok", "29909", "Madrid", "Spain", "99988877"))))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

    }
    @Test
    public void createAdmin() throws Exception {
        mockMvc.perform(post("/admin")
                .header("Authorization", token)
                .content(objectMapper.writeValueAsString(new UserRequest("admin@gmail.com", "superAdmin", "superAdmin")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }


}