package com.pawprints.cartservice.model.dto;

import com.pawprints.cartservice.model.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderStatusRequest {

    private OrderStatus orderStatus;
}
