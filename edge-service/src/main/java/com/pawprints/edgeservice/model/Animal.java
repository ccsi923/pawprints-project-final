package com.pawprints.edgeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawprints.edgeservice.model.enums.AnimalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;


@Data
@NoArgsConstructor
public class Animal {

    private Integer id;
    @Size(max = 20, min = 2)
    private String name;
    private AnimalType animal;
    @Min(0)
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal1 = (Animal) o;
        return Objects.equals(id, animal1.id) &&
                Objects.equals(name, animal1.name) &&
                animal == animal1.animal &&
                Objects.equals(age, animal1.age);
    }

}
