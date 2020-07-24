package com.pawprints.edgeservice.controller.dto;

import com.pawprints.edgeservice.model.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderStatusRequest {

    private OrderStatus orderStatus;

    public OrderStatusRequest(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
