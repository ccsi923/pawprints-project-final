package com.pawprints.materialservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.dto.OrderLineRequest;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.enums.OrderStatus;
import com.pawprints.materialservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderControllerImplTest {


    @Autowired
    private OrderControllerImpl orderController;

    @MockBean
    private OrderService orderService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void countAllOrdersByStatus() throws Exception {
        when(orderService.countAllOrdersByStatus()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/orders/by/status"))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderStatus() throws Exception {
        doNothing().when(orderService).updateOrderStatus(any(Integer.class),any(OrderStatus.class) );
        mockMvc.perform(MockMvcRequestBuilders.put("/update/order/1/status/REOPEN"))
                .andExpect(status().isNoContent());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/order")
                .content(objectMapper.writeValueAsString(new OrderRequest(new ArrayList<OrderLineRequest>(),"No comments")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void findAllOrders() throws Exception {
        when(orderService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());

    }
}