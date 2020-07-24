package com.pawprints.edgeservice.model;


import com.pawprints.edgeservice.model.enums.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class KitProduct {

    private Integer id;

    @Size(max = 255)
    private String name;
    private ProductType type;

    private Integer minStock;

    private Integer totalRemainingUnits;
    private Integer purchaseUnits;

    private BigDecimal pricePerUnit;

}
