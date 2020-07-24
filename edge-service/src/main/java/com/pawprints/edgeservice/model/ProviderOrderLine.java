package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProviderOrderLine {

    private Integer id;

    private ProviderOrder providerOrder;

    private Integer requestedQuantity;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    private LocalDate orderDate;

}
