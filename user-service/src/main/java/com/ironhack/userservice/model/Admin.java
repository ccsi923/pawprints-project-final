package com.ironhack.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
public class Admin extends User{

    public Admin(@Email(message = "Email must be valid") @NotEmpty String userEmail,
                 String username, @NotEmpty @Size(min = 4, message = "Minimum 4 characters") String password) {
        super(userEmail, username, password);
    }
}
