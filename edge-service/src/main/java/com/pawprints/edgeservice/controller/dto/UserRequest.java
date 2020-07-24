package com.pawprints.edgeservice.controller.dto;

import com.pawprints.edgeservice.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotNull
    @Email
    private String userEmail;
    @NotNull
    @Size(max = 20, min = 4, message = "Username should have between 4-20 characters")
    private String username;
    @NotNull
    @NotEmpty
    @Size(min = 4, message="Minimum 4 characters")
    private String password;

    @NotNull
    private Address primaryAddress;

    private Address mailingAddress;
}
