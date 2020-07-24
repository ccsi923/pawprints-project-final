package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Address {

    @NotNull
    private String addressStreet;

    @NotNull
    private String addressZipCode;

    @NotNull
    private String addressCity;

    @NotNull
    private String addressCountry;

    @NotNull
    @Pattern(regexp="([0-9]{9})", message = "Nine numbers")
    private String addressPhone;

}
