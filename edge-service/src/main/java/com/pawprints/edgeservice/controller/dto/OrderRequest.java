package com.pawprints.edgeservice.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private List<OrderLineRequest> orderLineRequests = new ArrayList<>();

    private String comments;

    public OrderRequest(@NotNull List<OrderLineRequest> orderLineRequests, String comments) {
        this.orderLineRequests = orderLineRequests;
        this.comments = comments;
    }
}
