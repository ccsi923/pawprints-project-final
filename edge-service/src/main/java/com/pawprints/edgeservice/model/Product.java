package com.pawprints.edgeservice.model;

import com.pawprints.edgeservice.model.enums.ProductCartType;
import com.pawprints.edgeservice.model.enums.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product {

    private ProductCartType productType;
    private BigDecimal productPrice;
    private Integer requestedQuantity;

}
