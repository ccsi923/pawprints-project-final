package com.pawprints.materialservice.controller.impl;

import com.pawprints.materialservice.model.ProviderOrder;
import com.pawprints.materialservice.model.dto.ProviderOrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProviderOrderController {

    public List<Object[]> countAllProviderOrdersByStatus();
    public List<ProviderOrder> findAllProviderOrder();
    public ProviderOrder createProviderOrder(ProviderOrderRequest providerOrderRequest);
}
