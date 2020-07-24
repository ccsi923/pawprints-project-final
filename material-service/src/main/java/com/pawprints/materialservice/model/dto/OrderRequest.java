package com.pawprints.materialservice.model.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private List<OrderLineRequest> orderLineRequests = new ArrayList<>();

    @NotNull
    private String comments;

    public OrderRequest(@NotNull List<OrderLineRequest> orderLineRequests, @NotNull String comments) {
        this.orderLineRequests = orderLineRequests;
        this.comments = comments;
    }
}
