package com.ironhack.userservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    /**
     * Attributes
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Email(message = "Email must be valid")
    @NotEmpty
    private String userEmail;
    @Column(unique = true)
    private String username;
    @NotEmpty
    @Size(min = 4, message="Minimum 4 characters")
    private String password;

    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy="user")
    private Set<Role> roles = new HashSet<>();

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Constructor Class
     * @param username a String value
     * @param password a String value
     * @param userEmail a String value
     */
    public User(@Email(message = "Email must be valid") @NotEmpty String userEmail,
                String username, @NotEmpty @Size(min = 4, message = "Minimum 4 characters") String password) {
        this.userEmail = userEmail;
        this.username = username;
        this.password = password;
    }
    /**
     * This method prints user with his properties
     *  @return A customize String format.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + roles +
                '}';
    }
}
