package com.pawprints.materialservice.model.dto;


import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.enums.ProductCartType;
import com.pawprints.materialservice.model.enums.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderLineRequest {

    @NotNull
    private ProductCartType productCartType;

    @NotNull
    @Min(1)
    private Integer requestedQuantity;

}
