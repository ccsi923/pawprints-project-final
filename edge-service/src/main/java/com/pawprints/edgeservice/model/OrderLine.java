package com.pawprints.edgeservice.model;

import com.pawprints.edgeservice.model.enums.ProductCartType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderLine {

    private Integer id;
    private ProductCartType productType;

    private Integer requestedQuantity;

}
