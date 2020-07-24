package com.pawprints.materialservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawprints.materialservice.model.enums.ProductCartType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductCartType productType;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    private Integer requestedQuantity;

    public OrderLine(ProductCartType product, Integer requestedQuantity, Order order) {
        this.productType = product;
        this.requestedQuantity = requestedQuantity;
        this.order = order;
    }
}
