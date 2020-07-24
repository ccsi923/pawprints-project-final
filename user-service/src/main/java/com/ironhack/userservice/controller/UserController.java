package com.ironhack.userservice.controller;

import com.ironhack.userservice.model.User;
import com.ironhack.userservice.model.dto.UserRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {
    public List<User> getUsers();
    public User getByUsername(String username);
    public User createClient(UserRequest userRequest);
    public User createUser(UserRequest userRequest);
}
