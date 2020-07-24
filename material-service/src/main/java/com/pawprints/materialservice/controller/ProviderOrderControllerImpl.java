package com.pawprints.materialservice.controller;

import com.pawprints.materialservice.controller.impl.ProviderOrderController;
import com.pawprints.materialservice.model.ProviderOrder;
import com.pawprints.materialservice.model.ProviderOrderLine;
import com.pawprints.materialservice.model.dto.ProviderOrderRequest;
import com.pawprints.materialservice.service.ProviderOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProviderOrderControllerImpl implements ProviderOrderController {

    @Autowired
    private ProviderOrderService providerOrderService;


    @GetMapping("/providerorders/by/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> countAllProviderOrdersByStatus() {
       return providerOrderService.countAllProviderOrdersByStatus();
    }

    @GetMapping("/providerorders")
    @ResponseStatus(HttpStatus.OK)
    public List<ProviderOrder> findAllProviderOrder(){
        return providerOrderService.findAll();
    }

    @PostMapping("/providerorder")
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderOrder createProviderOrder(@RequestBody ProviderOrderRequest providerOrderRequest){
        return providerOrderService.create(providerOrderRequest);
    }
}
