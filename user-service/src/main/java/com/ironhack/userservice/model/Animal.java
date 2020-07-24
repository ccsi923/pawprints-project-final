package com.ironhack.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.userservice.model.enums.AnimalType;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;


@Data
@Entity
@Table
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 20, min = 2)
    private String name;
    @Enumerated(EnumType.STRING)
    private AnimalType animal;
    @Min(0)
    private Integer age;

    @JsonIgnore
    @NotFound(
            action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Client client;


    public Animal(){}

    public Animal(@Size(max = 20, min = 2) String name, AnimalType animal, @Min(0) Integer age, Client client) {
        this.name = name;
        this.animal = animal;
        this.age = age;
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal1 = (Animal) o;
        return Objects.equals(id, animal1.id) &&
                Objects.equals(name, animal1.name) &&
                animal == animal1.animal &&
                Objects.equals(age, animal1.age) &&
                Objects.equals(client, animal1.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animal, age, client);
    }
}
