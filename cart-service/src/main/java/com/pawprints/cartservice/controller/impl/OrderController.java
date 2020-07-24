package com.pawprints.cartservice.controller.impl;

import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.model.dto.OrderStatusRequest;
import com.pawprints.cartservice.model.enums.OrderStatus;

import java.util.List;

public interface OrderController {

    public List<Order> findAllOrders();
    public List<Order> findOrderById(Integer id);
    public void updateOrderStatus(Integer id, OrderStatusRequest orderStatus);
    public List<Object[]> incomesPerDate();
    public List<Order> findCartsByStatus(OrderStatus cartStatus);
}
