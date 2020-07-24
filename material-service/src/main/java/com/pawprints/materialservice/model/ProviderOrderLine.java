package com.pawprints.materialservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ProviderOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private ProviderOrder providerOrder;

    private Integer requestedQuantity;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    private LocalDate orderDate;


    public BigDecimal totalCalc(){
        return product.getPricePerUnit().multiply(new BigDecimal(requestedQuantity));
    }

    public ProviderOrderLine(Product product, Integer requestedQuantity, ProviderOrder providerOrder) {
        this.product = product;
        this.requestedQuantity = requestedQuantity;
        this.price = totalCalc();
        this.orderDate = LocalDate.now();
        this.providerOrder = providerOrder;
    }

}
