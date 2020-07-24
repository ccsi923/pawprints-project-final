package com.pawprints.materialservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawprints.materialservice.model.dto.DateBetweenRequest;
import com.pawprints.materialservice.model.dto.OrderLineRequest;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.service.ProviderOrderLineService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProviderOrderLineControllerImplTest {

    @Autowired
    private ProviderOrderLineControllerImpl providerOrderLineController;

    @MockBean
    private ProviderOrderLineService providerOrderService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void findAllProviderOrderLineByOrderId() throws Exception {
        when(providerOrderService.findAllProviderOrderLineByOrderId(anyInt())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/providerorderlines/by/providerorder/1"))
                .andExpect(status().isOk());
    }

    @Test
    void expensesPerDate() throws Exception {
        when(providerOrderService.expensesPerDate()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/expenses/per/date"))
                .andExpect(status().isOk());
    }
}