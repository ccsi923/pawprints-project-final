package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Client extends User{

    @Valid
    @NotNull
    private Address primaryAddress;

    @Valid
    private Address mailingAddress;

    private List<Animal> animals = new ArrayList<Animal>();

}
