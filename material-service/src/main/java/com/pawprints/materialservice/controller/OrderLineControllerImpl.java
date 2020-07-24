package com.pawprints.materialservice.controller;

import com.pawprints.materialservice.controller.impl.OrderLineController;
import com.pawprints.materialservice.exceptions.NotFoundException;
import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderLineControllerImpl implements OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping("/orderlines/by/order/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderLine> findAllOrderLineByOrderId(@PathVariable("orderId") Integer ordId){

        return orderLineService.findByAllId(ordId);
    }

    @GetMapping("/quantities/by/product/type")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> sumQuantitiesByProductType(){
        return orderLineService.sumQuantitiesByProductType();
    }
}
