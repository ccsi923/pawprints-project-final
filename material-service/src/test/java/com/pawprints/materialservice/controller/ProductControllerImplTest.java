package com.pawprints.materialservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.enums.OrderStatus;
import com.pawprints.materialservice.model.enums.ProductType;
import com.pawprints.materialservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerImplTest {


    @Autowired
    private ProductControllerImpl productController;

    @MockBean
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findProductById() throws Exception {
        when(productService.findById(anyInt())).thenReturn(new Product());
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePurchaseUnitsByOrder() throws Exception {
        doNothing().when(productService).updatePurchaseUnitsByOrder(any(Integer.class));
        mockMvc.perform(MockMvcRequestBuilders.put("/update/purchaseunits/by/order/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void makeKitByProductType() throws Exception {
        doNothing().when(productService).makeKitByProductType(any(ProductType.class),any(Integer.class) );
        mockMvc.perform(MockMvcRequestBuilders.put("/makekit/by/producttype/RESULT_KIT/200"))
                .andExpect(status().isCreated());
    }
}