package com.ironhack.userservice.model.dto;

import com.ironhack.userservice.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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

    public UserRequest(@NotNull @Email String userEmail, @NotNull @Size(max = 20, min = 4, message = "Username should have between 4-20 characters") String username, @NotNull @NotEmpty @Size(min = 4, message = "Minimum 4 characters") String password, @NotNull Address primaryAddress, Address mailingAddress) {
       setUserEmail(userEmail);
       setUsername(username);
       setPassword(password);
       setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    public UserRequest(@NotNull @Email String userEmail, @NotNull @Size(max = 20, min = 4, message = "Username should have between 4-20 characters") String username, @NotNull @NotEmpty @Size(min = 4, message = "Minimum 4 characters") String password) {
        setUserEmail(userEmail);
        setUsername(username);
        setPassword(password);
    }
}
