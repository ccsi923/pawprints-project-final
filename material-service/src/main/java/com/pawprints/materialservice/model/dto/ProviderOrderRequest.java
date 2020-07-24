package com.pawprints.materialservice.model.dto;

import com.pawprints.materialservice.model.ProviderOrderLine;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProviderOrderRequest {

    @NotNull
    private List<ProviderOrderLineRequest> providerOrderLines = new ArrayList<ProviderOrderLineRequest>();

    @NotNull
    private String comments;
}
