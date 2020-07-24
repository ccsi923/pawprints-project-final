package com.ironhack.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Client extends User{

    /**
     * Attributes
     */

    @Valid
    @NotNull
    @Embedded
    private Address primaryAddress;

    @Valid
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressStreet", column = @Column(name = "mailingStreet")),
            @AttributeOverride(name = "addressCountry", column = @Column(name = "mailingCountry")),
            @AttributeOverride(name = "addressCity", column = @Column(name = "mailingCity")),
            @AttributeOverride(name = "addressZipCode", column = @Column(name = "mailingZipCode")),
            @AttributeOverride(name = "addressPhone", column = @Column(name = "mailingPhone"))
    })
    private Address mailingAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch= FetchType.EAGER)
    private List<Animal> animals = new ArrayList<Animal>();

    public Client(@Email(message = "Email must be valid") @NotEmpty String userEmail,
                  String username, @NotEmpty @Size(min = 4, message = "Minimum 4 characters") String password,
                  @Valid @NotNull Address primaryAddress, @Valid Address mailingAddress) {
        super(userEmail, username, password);
       setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }
}
