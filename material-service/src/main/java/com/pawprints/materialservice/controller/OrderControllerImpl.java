package com.pawprints.materialservice.controller;

import com.pawprints.materialservice.controller.impl.OrderController;
import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.enums.OrderStatus;
import com.pawprints.materialservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/orders/by/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> countAllOrdersByStatus(){
       return orderService.countAllOrdersByStatus();
    }

    @PutMapping("/update/order/{orderid}/status/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable("orderid") Integer id, @PathVariable("status") OrderStatus orderStatus){
        orderService.updateOrderStatus(id, orderStatus);
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders(){
        return orderService.findAll();
    }
}
