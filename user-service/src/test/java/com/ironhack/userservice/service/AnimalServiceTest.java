package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.NotFoundException;
import com.ironhack.userservice.model.Address;
import com.ironhack.userservice.model.Animal;
import com.ironhack.userservice.model.Client;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.model.dto.AnimalRequest;
import com.ironhack.userservice.model.dto.UserRequest;
import com.ironhack.userservice.model.enums.AnimalType;
import com.ironhack.userservice.repository.AnimalRepository;
import com.ironhack.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimalRepository animalRepository;

    private UserRequest client;
    private Client savedClient;
    private Animal animal;
    private AnimalRequest animalRequest;

    @BeforeEach
    void setUp(){
        client = new UserRequest(
                "cristia@gmail.com", "superCris", "superCris",
                new Address("tik tok", "29909", "Madrid", "Spain", "99988877"),
                new Address("tik tok", "29909", "Madrid", "Spain", "99988877"));
        savedClient = userService.createClient(client);
        animalRequest = new AnimalRequest(savedClient.getId(), "superCris", AnimalType.CAT,2);
        animal = animalService.createAnimal(animalRequest);
    }
    @AfterEach
    void tearDown(){
        animalRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void findAllByOwner() throws Exception {
        assertEquals(1, animalService.findAllByOwner(savedClient.getId()).size());    }

    @Test
    void createAnimal() throws Exception {
        Animal animal2 = animalService.createAnimal(animalRequest);
        assertEquals(animal2.getAge(), 2);
    };

    @Test
    void deleteAnimal() throws Exception {
       // when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(client));
        animalService.deleteAnimal(animal.getId());
    }

}