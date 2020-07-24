package com.ironhack.userservice.controller;

import com.ironhack.userservice.model.Animal;
import com.ironhack.userservice.model.dto.AnimalRequest;

import java.util.List;

public interface AnimalController {

    public List<Animal> findAllByOwner(Long ownerId);
    public Animal createAnimal(AnimalRequest animalRequest);
    public void deleteAnimal(Integer animalId);
}
