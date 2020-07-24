package com.pawprints.edgeservice.controller;

import com.pawprints.edgeservice.model.Order;
import com.pawprints.edgeservice.model.User;
import com.pawprints.edgeservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Login Controller")
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation(value="Login",
            notes = "Login",
            response = UserDetails.class)
    @ResponseStatus(HttpStatus.OK)
    public UserDetails login(@AuthenticationPrincipal User user) {
        return userService.loadUserByUsername(user.getUsername());
    }

}
