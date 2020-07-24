package com.pawprints.cartservice.model.dto;

import com.pawprints.cartservice.model.enums.PaymentType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartRequest {


    private Integer customerId;
    @NotNull
    private Integer animalId;

    @NotNull
    private PaymentType paymentType;

    private List<ProductRequest> products = new ArrayList<ProductRequest>();

}
