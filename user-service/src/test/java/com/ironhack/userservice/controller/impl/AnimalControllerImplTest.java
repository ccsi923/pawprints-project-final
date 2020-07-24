package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.controller.AnimalController;
import com.ironhack.userservice.controller.UserController;
import com.ironhack.userservice.exceptions.NotFoundException;
import com.ironhack.userservice.model.Address;
import com.ironhack.userservice.model.Animal;
import com.ironhack.userservice.model.dto.AnimalRequest;
import com.ironhack.userservice.model.dto.UserRequest;
import com.ironhack.userservice.model.enums.AnimalType;
import com.ironhack.userservice.service.AnimalService;
import com.ironhack.userservice.service.UserService;
import com.ironhack.userservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AnimalControllerImplTest {
    @Autowired
    private AnimalController animalController;
    @MockBean
    private AnimalService animalService;

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
    void findAllByOwner() throws Exception {
        when(animalService.findAllByOwner(Mockito.anyLong())).thenReturn(new ArrayList<Animal>());
        mockMvc.perform(get("/animals/owner/1").header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void createAnimal() throws Exception {
        mockMvc.perform(post("/animal")
                .header("Authorization", token)
                .content(objectMapper.writeValueAsString(new AnimalRequest((long) 1, "superCris", AnimalType.CAT,2)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void deleteAnimal() throws Exception {
        doNothing().when(animalService).deleteAnimal(any(Integer.class));
        mockMvc.perform(MockMvcRequestBuilders.delete("/animal/1").header("Authorization", token))
                .andExpect(status().isNoContent());
    }
}