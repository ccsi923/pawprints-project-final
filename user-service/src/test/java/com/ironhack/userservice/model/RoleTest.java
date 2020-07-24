package com.ironhack.userservice.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role;
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User("cc@gmail.com","admin", "admin");
        role = new Role("ROLE_ADMIN", user);
    }

    @AfterEach
    public void tearDown(){
        role = null;
        user = null;
    }


    @Test
    public void setGetId_roleSetGetId_longId(){
        Long id = 3L;
        role.setId(id);
        assertEquals(id, role.getId());
    }

    @Test
    public void setGetRole_roleSetGetRole_stringRole(){
        String roleString = "ROLE_SALESREP";
        role.setRole(roleString);
        assertEquals(roleString, role.getRole());
    }

    @Test
    public void setUserRole_roleSetGetUserRole_stringRole(){
        role.setUser(user);
        assertEquals(user, role.getUser());
    }

    @Test
    public void stringRole_toStringRole_string(){
        String roleString = role.toString();
        assertEquals(roleString, role.toString());
    }

}