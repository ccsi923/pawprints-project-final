package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class User {

    private Long id;

    @Email(message = "Email must be valid")
    @NotEmpty
    private String userEmail;

    private String username;
    @NotEmpty
    @Size(min = 4, message="Minimum 4 characters")
    private String password;

    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    public User(@Email(message = "Email must be valid") @NotEmpty String userEmail,
                String username, @NotEmpty @Size(min = 4, message = "Minimum 4 characters") String password) {
        this.userEmail = userEmail;
        this.username = username;
        this.password = password;
    }
}
