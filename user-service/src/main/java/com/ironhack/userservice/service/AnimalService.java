package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.NotFoundException;
import com.ironhack.userservice.model.Animal;
import com.ironhack.userservice.model.Client;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.model.dto.AnimalRequest;
import com.ironhack.userservice.repository.AnimalRepository;
import com.ironhack.userservice.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private static final Logger LOGGER = LogManager.getLogger(AnimalService.class);


    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Animal> findAllByOwner(Long ownerId) {
        LOGGER.info("INIT - findAllByOwner");

       User user =   userRepository.findById(ownerId)
               .orElseThrow(() -> new NotFoundException("User with id " + ownerId + " not found" ));
        LOGGER.info("User with id " + ownerId + " found");
       if(user instanceof Client){
           LOGGER.info("END - findAllByOwner");
           return ((Client) user).getAnimals();
       }
        LOGGER.error("User with id " + ownerId + " doesn't have animals associated");
       throw new NotFoundException("User with id " + ownerId + " doesn't have animals associated" );

    }

    public Animal createAnimal(AnimalRequest animalRequest){
        LOGGER.info("INIT - createAnimal");

        User user =  userRepository.findById(animalRequest.getClientId())
                .orElseThrow(() -> new NotFoundException("User with id " + animalRequest.getClientId() + " not found" ));
        LOGGER.info("User with id " + animalRequest.getClientId() + " found");
        if(user instanceof Client){
          //List<Animal> animals =  ((Client) user).getAnimals();
          Animal animal = new Animal(animalRequest.getName(), animalRequest.getAnimal(),
                  animalRequest.getAge(), ((Client) user) );
          //animals.add(animal);
          //((Client) user).setAnimals(animals);
          //userRepository.save(user);
            LOGGER.info("END - createAnimal");
          return animalRepository.save(animal);
        }
        LOGGER.error("User with id " + animalRequest.getClientId() + " doesn't have animals associated");
        throw new NotFoundException("User with id " + animalRequest.getClientId() + " doesn't have animals associated" );
    }

    public void deleteAnimal(Integer animalId){
        LOGGER.info("INIT - deleteAnimal");

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(()-> new
                        NotFoundException("Animal with id " + animalId + " not found" ));
        Client client = (Client) userRepository.findById(animal.getClient().getId()).orElseThrow(() ->  new NotFoundException("User not found" ) );
        client.getAnimals().remove(animal);
        animalRepository.delete(animal);
        LOGGER.info("END - deleteAnimal");
        //userRepository.save(client);
    }
}
