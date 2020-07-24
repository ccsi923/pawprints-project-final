package com.pawprints.edgeservice.controller.dto;

import com.pawprints.edgeservice.model.Product;
import com.pawprints.edgeservice.model.enums.PaymentType;
import com.pawprints.edgeservice.model.enums.ProductCartType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartRequest {

    private Long customerId;
    @NotNull
    private Integer animalId;

    @NotNull
    private PaymentType paymentType;

    private List<Product> products = new ArrayList<Product>();

    private String comments;
}
