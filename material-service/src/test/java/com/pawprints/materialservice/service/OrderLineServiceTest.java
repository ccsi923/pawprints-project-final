package com.pawprints.materialservice.service;

import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.model.enums.ProductCartType;
import com.pawprints.materialservice.repository.OrderLineRepository;
import com.pawprints.materialservice.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderLineServiceTest {

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private OrderLineRepository orderLineRepository;


    @Autowired
    private OrderRepository orderRepository;

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
    void findByAllId() {
        List<OrderLine> orderLine2 = orderLineService.findByAllId(order.getId());
        assertEquals(1, orderLine2.size());
    }

    @Test
    void sumQuantitiesByProductType() {
        List<Object[]> objects = orderLineService.sumQuantitiesByProductType();
        assertEquals(1, objects.size());
    }
}