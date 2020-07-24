package com.pawprints.cartservice.controller.impl;

import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.dto.CartRequest;
import com.pawprints.cartservice.model.enums.OrderStatus;

import java.util.List;

public interface CartController {

    public Cart createCart(CartRequest cartRequest);
    public List<Cart> findAllCarts();
    public void deleteCart(Integer id);

}
