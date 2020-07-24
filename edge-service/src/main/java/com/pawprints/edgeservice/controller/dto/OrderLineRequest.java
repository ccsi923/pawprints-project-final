package com.pawprints.edgeservice.controller.dto;

import com.pawprints.edgeservice.model.enums.ProductCartType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderLineRequest {

    @NotNull
    private ProductCartType productCartType;

    @NotNull
    @Min(1)
    private Integer requestedQuantity;

    public OrderLineRequest(@NotNull ProductCartType productCartType, Integer requestedQuantity) {
        this.productCartType = productCartType;
        this.requestedQuantity = requestedQuantity;
    }
}
