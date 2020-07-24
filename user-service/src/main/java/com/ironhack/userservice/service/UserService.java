package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.NotFoundException;
import com.ironhack.userservice.model.Admin;
import com.ironhack.userservice.model.Client;
import com.ironhack.userservice.model.Role;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.model.dto.UserRequest;
import com.ironhack.userservice.repository.RoleRepository;
import com.ironhack.userservice.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User getByUsername(String username) {
        LOGGER.info("INIT - getByUsername");
        LOGGER.info("END - getByUsername");
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("There's no user with provided username"));
    }

    public List<User> getAll() {
        LOGGER.info("INIT - getAll");
        LOGGER.info("END - getAll");
        return userRepository.findAll();
    }

    public Client createClient(UserRequest userRequest){
        LOGGER.info("INIT - createClient");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Client client = new Client(userRequest.getUserEmail(),userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getPrimaryAddress(), userRequest.getMailingAddress());
        Client client1 = userRepository.save(client);
        LOGGER.info("Saved client witht id " + client1.getId());
        Role role = new Role("ROLE_CLIENT", client);
        roleRepository.save(role);
        LOGGER.info("Saved client role ");
        LOGGER.info("END - createClient");
        return client1;
    }

    public Admin createAdmin(UserRequest userRequest){
        LOGGER.info("INIT - createAdmin");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin admin = new Admin(userRequest.getUserEmail(),userRequest.getUsername(), passwordEncoder.encode(userRequest.getPassword()));
        Admin admin1 = userRepository.save(admin);
        LOGGER.info("Saved admin witht id " + admin1.getId());
        Role role = new Role("ROLE_ADMIN", admin);
        Role role2 = new Role("ROLE_CLIENT", admin);
        roleRepository.save(role);
        roleRepository.save(role2);
        LOGGER.info("Saved admin roles ");
        LOGGER.info("END - createAdmin");
        return admin1;
    }
}
