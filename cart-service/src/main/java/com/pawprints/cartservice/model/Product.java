package com.pawprints.cartservice.model;

import com.pawprints.cartservice.model.enums.ProductType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@Embeddable
public class Product {

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private BigDecimal productPrice;
    private Integer requestedQuantity;

    public Product() {
    }

    public Product(ProductType productType, Integer requestedQuantity) {
        this.productType = productType;
        this.requestedQuantity = requestedQuantity;
        setProductPrice(productType);
    }

    public void setProductPrice(ProductType productType) {
        if(productType.equals(ProductType.HEALTH) ){
            this.productPrice = new BigDecimal(199);
        }
        if(productType.equals(ProductType.LINEAGE) ){
            this.productPrice = new BigDecimal(129);
        }
        if(productType.equals(ProductType.PAWPRINTS) ){
            this.productPrice = new BigDecimal(279);
        }
    }

}
