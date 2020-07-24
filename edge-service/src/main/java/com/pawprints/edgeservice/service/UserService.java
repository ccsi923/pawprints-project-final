package com.pawprints.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pawprints.edgeservice.client.UserClient;
import com.pawprints.edgeservice.controller.dto.AnimalRequest;
import com.pawprints.edgeservice.controller.dto.UserRequest;
import com.pawprints.edgeservice.exceptions.NoPossibleCreateAnimal;
import com.pawprints.edgeservice.exceptions.UserClientNotWorkingException;
import com.pawprints.edgeservice.model.Animal;
import com.pawprints.edgeservice.model.Client;
import com.pawprints.edgeservice.model.User;
import com.pawprints.edgeservice.security.CustomSecuredUser;
import com.pawprints.edgeservice.util.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserClient userClient;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    //@HystrixCommand(fallbackMethod = "notLoadByUsername")
    public UserDetails loadUserByUsername(String username) {
        LOGGER.info("INIT - loadUserByUsername");

        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        LOGGER.info("Search user with username: " + username);
        try {
            User user = userClient.getByUsername(userToken,username);
            return new CustomSecuredUser(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UsernameNotFoundException("Invalid username/password combination.");
        }
                /*new CustomSecuredUser(user.orElseThrow(() ->
                new UsernameNotFoundException("Invalid username/password combination.")));*/
    }


    public UserDetails notLoadByUsername(String username) {
        LOGGER.error("user-service not available!");

        throw new UserClientNotWorkingException("user-service not available!");
    }

    @HystrixCommand(fallbackMethod = "cantCreateClient")
    public User createClient(UserRequest userRequest){
        LOGGER.info("INIT - createClient");

        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        return userClient.createClient(userToken,userRequest);
    }

    public User cantCreateClient(UserRequest userRequest) {
        LOGGER.error("user-service not available!");
        throw new UserClientNotWorkingException("user-service not available!");
    }

    @Secured("ROLE_ADMIN")
    @HystrixCommand(fallbackMethod = "cantCrateAdmin")
    public User createUser(UserRequest userRequest){
        LOGGER.info("INIT - createUser");

        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        return userClient.createUser(userToken,userRequest);
    }

    public User cantCrateAdmin(UserRequest userRequest) {
        LOGGER.error("user-service not available!");
        throw new UserClientNotWorkingException("user-service not available!");
    }


    //@HystrixCommand(fallbackMethod = "cantFindAllByOwner")
    public List<Animal> findAllByOwner(User user){
        LOGGER.info("INIT - findAllByOwner");

        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        return userClient.findAllByOwner(userToken,user.getId());
    }

    public List<Animal>  cantFindAllByOwner(Long ownerId) {
        LOGGER.error("user-service not available!");
        throw new UserClientNotWorkingException("user-service not available!");
    }



    //@HystrixCommand(fallbackMethod = "cantCreateAnimal")
    public Animal createAnimal(User user ,AnimalRequest animalRequest){
        LOGGER.info("INIT - createAnimal");
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        if( user.getRoles().size() == 1 ){
            animalRequest.setClientId(user.getId());
            LOGGER.info("EMD - createAnimal");
            return userClient.createAnimal(userToken,animalRequest);
        }
        LOGGER.info("ERROR - The user isn't a client");
        throw new NoPossibleCreateAnimal("The user isn't a client");

    }

    public Animal cantCreateAnimal(AnimalRequest animalRequest) {
        LOGGER.error("user-service not available!");
        throw new UserClientNotWorkingException("user-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantDeleteAnimal")
    public void deleteAnimal(Integer animalId){
        LOGGER.info("INIT - deleteAnimal");
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        userClient.deleteAnimal(userToken,animalId);
    }

    public void cantDeleteAnimal(Integer animalId) {
        LOGGER.error("user-service not available!");
        throw new UserClientNotWorkingException("user-service not available!");
    }


}

