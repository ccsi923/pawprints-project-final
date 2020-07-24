package com.pawprints.materialservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.dto.OrderLineRequest;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.dto.ProviderOrderRequest;
import com.pawprints.materialservice.service.ProviderOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProviderOrderControllerImplTest {

    @Autowired
    private ProviderOrderControllerImpl providerOrderController;

    @MockBean
    private ProviderOrderService providerOrderService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void countAllProviderOrdersByStatus() throws Exception {
        when(providerOrderService.countAllProviderOrdersByStatus()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/providerorders/by/status"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllProviderOrder() throws Exception {
        when(providerOrderService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/providerorders"))
                .andExpect(status().isOk());
    }

    @Test
    void createProviderOrder() throws Exception {
        mockMvc.perform(post("/providerorder")
                .content(objectMapper.writeValueAsString(new ProviderOrderRequest()))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }
}