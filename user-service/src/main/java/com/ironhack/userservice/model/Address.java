package com.ironhack.userservice.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
public class Address {


    private String addressStreet;


    private String addressZipCode;


    private String addressCity;


    private String addressCountry;

   // @Pattern(regexp="(^(6|7|9){1}([0-9]){8}$)", message = "Nine numbers")
    private String addressPhone;

    public Address() {
    }

    public Address(String addressStreet, String addressZipCode, String addressCity, String addressCountry, String addressPhone) {
       setAddressStreet(addressStreet);
       setAddressZipCode(addressZipCode);
        setAddressCity(addressCity);
        setAddressCountry(addressCountry);
        setAddressPhone(addressPhone);
    }
}
