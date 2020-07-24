package com.pawprints.edgeservice.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProviderOrderRequest {

    @NotNull
    private List<ProviderOrderLineRequest> providerOrderLines = new ArrayList<ProviderOrderLineRequest>();

    @NotNull
    private String comments;
}
