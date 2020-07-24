package com.pawprints.materialservice.controller.impl;

import com.pawprints.materialservice.model.OrderLine;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderLineController {

    public List<OrderLine> findAllOrderLineByOrderId(Integer ordId);
    public List<Object[]> sumQuantitiesByProductType();
}
