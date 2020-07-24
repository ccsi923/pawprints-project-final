package com.pawprints.materialservice.model;

import com.pawprints.materialservice.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private Integer minStock;

    private Integer totalRemainingUnits;
    private Integer purchaseUnits;

    private BigDecimal pricePerUnit;

}
