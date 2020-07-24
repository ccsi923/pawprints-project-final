package com.pawprints.cartservice.controller;

import com.pawprints.cartservice.controller.impl.OrderController;
import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.model.dto.OrderStatusRequest;
import com.pawprints.cartservice.model.enums.OrderStatus;
import com.pawprints.cartservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/order/{ordid}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findOrderById(@PathVariable("ordid") Integer id){
        return orderService.findAllByCustomerId(id);
    }

    @PutMapping("/order/update/status/{ordid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable("ordid") Integer id, @RequestBody OrderStatusRequest orderStatusRequest){
        orderService.updateStatus(id, orderStatusRequest);
    }

    @GetMapping("/incomes/perday")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> incomesPerDate(){
        return orderService.incomesPerDate();
    }


    @GetMapping("/carts/by/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findCartsByStatus(@PathVariable("status") OrderStatus cartStatus){
        return orderService.findAllByStatus(cartStatus);
    }

}
