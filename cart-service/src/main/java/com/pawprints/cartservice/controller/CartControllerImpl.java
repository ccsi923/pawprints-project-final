package com.pawprints.cartservice.controller;

import com.pawprints.cartservice.controller.impl.CartController;
import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.model.dto.CartRequest;
import com.pawprints.cartservice.model.enums.OrderStatus;
import com.pawprints.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartControllerImpl implements CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCart(@RequestBody CartRequest cartRequest){
        return cartService.create(cartRequest);
    }

    @GetMapping("/carts")
    @ResponseStatus(HttpStatus.OK)
    public List<Cart> findAllCarts(){
        return cartService.findAll();
    }

    @DeleteMapping("/cart/{cartid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable("cartid") Integer id){
        cartService.deleteCart(id);
    }



}
