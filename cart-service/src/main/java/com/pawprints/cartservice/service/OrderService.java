package com.pawprints.cartservice.service;

import com.pawprints.cartservice.exceptions.NotFoundException;
import com.pawprints.cartservice.exceptions.StatusClosedException;
import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.model.dto.OrderStatusRequest;
import com.pawprints.cartservice.model.enums.OrderStatus;
import com.pawprints.cartservice.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);


    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        LOGGER.info("INIT - findAll");
        LOGGER.info("END - findAll");
        return orderRepository.findAll();
    }

    public List<Order> findAllByCustomerId(Integer id){
        LOGGER.info("INIT - findAllByCustomerId");
        LOGGER.info("END - findAllByCustomerId");
        return orderRepository.findAllByUserId(id);
    }

    public void updateStatus(Integer id, OrderStatusRequest orderStatus){
        LOGGER.info("INIT - updateStatus");

        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));

        order.setStatusOrder(orderStatus.getOrderStatus());
        orderRepository.save(order);
        LOGGER.info("END - updateStatus");
    }

    public List<Object[]> incomesPerDate(){
        LOGGER.info("INIT - incomesPerDate");
        LOGGER.info("END - incomesPerDate");
        return orderRepository.incomesPerDate();
    }

    public List<Order> findAllByStatus(OrderStatus cartStatus) {
        LOGGER.info("INIT - findAllByStatus");
        LOGGER.info("END - findAllByStatus");
        return orderRepository.findAllByStatusOrder(cartStatus);
    }

}
