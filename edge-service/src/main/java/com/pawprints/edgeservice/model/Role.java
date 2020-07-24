package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Role {

    private Long id;
    private String role;

    private User user;

}
