package com.pawprints.edgeservice.client;

import com.pawprints.edgeservice.controller.dto.AnimalRequest;
import com.pawprints.edgeservice.controller.dto.UserRequest;
import com.pawprints.edgeservice.model.Animal;
import com.pawprints.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="user-service", url = "https://user-pawprints-service.herokuapp.com/")
public interface UserClient {

    @GetMapping("/animals/owner/{id}")
    public List<Animal> findAllByOwner(@RequestHeader(name = "Authorization") String token, @PathVariable("id") Long ownerId);

    @PostMapping("/animal")
    public Animal createAnimal(@RequestHeader(name = "Authorization") String token, @RequestBody AnimalRequest animalRequest);

    @DeleteMapping("/animal/{id}")
    public void deleteAnimal(@RequestHeader(name = "Authorization") String token, @PathVariable("id") Integer animalId);

    @GetMapping("/users")
    public List<User> getUsers(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/users/{username}")
    public User getByUsername(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "username") String username);

    @PostMapping("/client")
    public User createClient(@RequestHeader(name = "Authorization") String token, @RequestBody UserRequest userRequest);

    @PostMapping("/admin")
    public User createUser(@RequestHeader(name = "Authorization") String token, @RequestBody UserRequest userRequest);


}
