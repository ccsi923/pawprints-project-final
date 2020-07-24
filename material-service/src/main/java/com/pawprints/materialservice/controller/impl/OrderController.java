package com.pawprints.materialservice.controller.impl;

import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.enums.OrderStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderController {

    public List<Object[]> countAllOrdersByStatus();
    public void updateOrderStatus(Integer id,OrderStatus orderStatus);
    public Order create(OrderRequest orderRequest);
    public List<Order> findAllOrders();
}
