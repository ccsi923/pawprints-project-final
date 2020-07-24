package com.pawprints.materialservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawprints.materialservice.controller.impl.OrderLineController;
import com.pawprints.materialservice.service.OrderLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderLineControllerImplTest {

    @Autowired
    private OrderLineController orderLineController;

    @MockBean
    private OrderLineService orderLineService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findAllOrderLineByOrderId() throws Exception {
        when(orderLineService.findByAllId(anyInt())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/orderlines/by/order/1"))
                .andExpect(status().isOk());
    }

    @Test
    void sumQuantitiesByProductType() throws Exception {
        when(orderLineService.sumQuantitiesByProductType()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/quantities/by/product/type"))
                .andExpect(status().isOk());
    }
}