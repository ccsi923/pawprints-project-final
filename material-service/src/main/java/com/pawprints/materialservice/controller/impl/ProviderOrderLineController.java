package com.pawprints.materialservice.controller.impl;

import com.pawprints.materialservice.model.ProviderOrderLine;
import com.pawprints.materialservice.model.dto.DateBetweenRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProviderOrderLineController {

    public List<ProviderOrderLine> findAllProviderOrderLineByOrderId(Integer probId);
    public List<Object[]> expensesPerDateBetween(DateBetweenRequest dateBetweenRequest);
    public List<Object[]> expensesPerDate();
}
