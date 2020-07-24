package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Cart {
    private Integer id;

    private Long customerId;
    private BigDecimal subtotal;
    private LocalDate purchaseDate;



}
