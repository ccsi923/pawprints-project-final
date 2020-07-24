package com.ironhack.userservice.model.dto;


import com.ironhack.userservice.model.enums.AnimalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class AnimalRequest {

    @NotNull
    private Long clientId;
    @NotBlank
    @Size(max = 20, min = 2)
    private String name;
    @NotNull
    private AnimalType animal;
    @Min(0)
    private Integer age;

    public AnimalRequest(@NotNull Long clientId, @NotBlank @Size(max = 20, min = 2) String name, @NotNull AnimalType animal, @Min(0) Integer age) {
        setClientId(clientId);
        setName(name);
        setAnimal(animal);
        setAge(age);
    }
}
