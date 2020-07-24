package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.AnimalController;
import com.ironhack.userservice.model.Animal;
import com.ironhack.userservice.model.dto.AnimalRequest;
import com.ironhack.userservice.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
public class AnimalControllerImpl implements AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/animals/owner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAllByOwner(@PathVariable("id") Long ownerId){
        return animalService.findAllByOwner(ownerId);
    }

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal createAnimal(@RequestBody AnimalRequest animalRequest){
        return animalService.createAnimal(animalRequest);
    }

    @DeleteMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable("id") Integer animalId){
        animalService.deleteAnimal(animalId);
    }
}
