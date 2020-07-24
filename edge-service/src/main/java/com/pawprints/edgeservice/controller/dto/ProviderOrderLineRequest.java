package com.pawprints.edgeservice.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProviderOrderLineRequest {

    @NotNull
    private Integer productId;

    @NotNull
    @Min(1)
    private Integer requestedQuantity;
}
