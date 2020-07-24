package com.pawprints.materialservice.service;

import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.enums.OrderStatus;
import com.pawprints.materialservice.model.enums.ProductCartType;
import com.pawprints.materialservice.repository.OrderLineRepository;
import com.pawprints.materialservice.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    private Order order;

    private OrderLine orderLine;

    @BeforeEach
    public void setUp() {
        order = new Order("Life is hard");
        orderLine = new OrderLine(ProductCartType.HEALTH, 100, order);
        orderLineRepository.save(orderLine);
    }

    @AfterEach
    public void tearDown(){
        orderLineRepository.deleteAll();


    }

    @Test
    void findAll() {
        List<Order> orders = orderService.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    void create() {
        Order order = orderService.create(new OrderRequest(new ArrayList<>(),"No comments"));
        assertEquals("No comments", order.getComments());
    }

    @Test
    void updateOrderStatus() {
        orderService.updateOrderStatus(order.getId(), OrderStatus.CLOSED);
    }

    @Test
    void countAllOrdersByStatus() {
        List<Object[]> objects = orderService.countAllOrdersByStatus();
        assertFalse(objects.contains("CLOSED"));
    }
}