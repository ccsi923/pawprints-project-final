package com.pawprints.edgeservice.controller;

import com.pawprints.edgeservice.controller.dto.AnimalRequest;
import com.pawprints.edgeservice.controller.dto.UserRequest;
import com.pawprints.edgeservice.model.Animal;
import com.pawprints.edgeservice.model.User;
import com.pawprints.edgeservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User Controller")
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/client")
    @ApiOperation(value="Create a client",
            notes = "Create a client",
            response = User.class)
    @ResponseStatus(HttpStatus.CREATED)
    public User createClient(@RequestBody UserRequest userRequest) {
        return userService.createClient(userRequest);
    }

    @PostMapping("/admin")
    @ApiOperation(value="Create an admin",
            notes = "Create an admin",
            response = User.class)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/animals/owner")
    @ApiOperation(value="Find all animals by owner",
            notes = "Find all animals by owner",
            response = Animal.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAllByOwner(@AuthenticationPrincipal User user){
        return userService.findAllByOwner(user);
    }

    @PostMapping("/animal")
    @ApiOperation(value="Create an animal associated with an user",
            notes = "Create an animal associated with an user",
            response = Animal.class)
    @ResponseStatus(HttpStatus.OK)
    public Animal createAnimal(@AuthenticationPrincipal User user, @RequestBody AnimalRequest animalRequest){
        return userService.createAnimal(user ,animalRequest);
    }

    @DeleteMapping("/animal/{id}")
    @ApiOperation(value="Retrieve expenses per date",
            notes = "Retrieve expenses per date")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable("id") Integer animalId){
        userService.deleteAnimal(animalId);
    }

}
