package com.pawprints.cartservice.model.dto;

import com.pawprints.cartservice.model.enums.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductRequest {

    private ProductType productType;

    private BigDecimal productPrice;
    private Integer requestedQuantity;
}
