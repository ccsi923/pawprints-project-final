package com.pawprints.cartservice.controller;

import com.pawprints.cartservice.controller.impl.CartItemController;
import com.pawprints.cartservice.model.CartItem;
import com.pawprints.cartservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartItemControllerImpl implements CartItemController {


    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cartitems")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllCartItems(){
        return cartItemService.findAll();
    }
    @GetMapping("/cartitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartItem findCartItemById(@PathVariable("id") Integer itemId){
        return cartItemService.findById(itemId);
    }

    @GetMapping("/cartitem/by/cart/{cartid}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllCartItemsByCar(@PathVariable("cartid") Integer cartId){
        return cartItemService.findAllByCar(cartId);
    }

}
