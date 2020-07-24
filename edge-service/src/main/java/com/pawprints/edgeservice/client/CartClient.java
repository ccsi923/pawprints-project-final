package com.pawprints.edgeservice.client;

import com.pawprints.edgeservice.controller.dto.CartRequest;
import com.pawprints.edgeservice.controller.dto.OrderStatusRequest;
import com.pawprints.edgeservice.model.Cart;
import com.pawprints.edgeservice.model.CartItem;
import com.pawprints.edgeservice.model.Order;
import com.pawprints.edgeservice.model.enums.OrderStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="cart-service", url = "https://cart-pawprint-service.herokuapp.com/")
public interface CartClient {

    @PostMapping("/cart")
    public Cart createCart(@RequestBody CartRequest cartRequest);

    @GetMapping("/carts")
    public List<Cart> findAllCarts();

    @DeleteMapping("/cart/{cartid}")
    public void deleteCart(@PathVariable("cartid") Integer id);

    @GetMapping("/cartitems")
    public List<CartItem> findAllCartItems();

    @GetMapping("/cartitem/{id}")
    public CartItem findCartItemById(@PathVariable("id") Integer itemId);

    @GetMapping("/cartitem/by/cart/{cartid}")
    public List<CartItem> findAllCartItemsByCar(@PathVariable("cartid") Integer cartId);

    @GetMapping("/orders")
    public List<Order> findAllOrders();

    @GetMapping("/order/{cusid}")
    public List<Order> findOrdersByCustomerId(@PathVariable("cusid") Integer id);

    @PutMapping("/order/update/status/{ordid}")
    public void updateOrderStatus(@PathVariable("ordid") Integer id, @RequestBody OrderStatusRequest orderStatusRequest);

    @GetMapping("/incomes/perday")
    public List<Object[]> incomesPerDate();

    @GetMapping("/carts/by/status/{status}")
    public List<Order> findCartsByStatus(@PathVariable("status") OrderStatus cartStatus);
}
