package com.ironhack.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
    /**
     * Attributes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToOne
    @JsonIgnore
    private User user;

    /**
     * Default Constructor
     */
    public Role() {}

    /**
     * Constructor
     * @param role A String value
     */
    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    /**Getters & Setters**/

    /**
     * This method gets Role's id
     * @return a integer value
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets Role's id
     * @param id a long value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  This method gets Role's role
     * @return a Role element
     */
    public String getRole() {
        return role;
    }

    /**
     * This method sets Role's role
     * @param role A String value
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method gets Role's user
     * @return a User element
     */
    public User getUser() {
        return user;
    }

    /**
     * This method sets Role's user
     * @param user a User element
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * This method generate a string with a customize format.
     * @return a String value
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + role + '\'';
    }
}
