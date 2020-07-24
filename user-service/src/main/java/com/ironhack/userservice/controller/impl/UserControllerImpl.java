package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.UserController;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.model.dto.UserRequest;
import com.ironhack.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public User getByUsername(@PathVariable(name = "username") String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public User createClient(@RequestBody UserRequest userRequest) {
        return userService.createClient(userRequest);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserRequest userRequest) {
        return userService.createAdmin(userRequest);
    }


}
