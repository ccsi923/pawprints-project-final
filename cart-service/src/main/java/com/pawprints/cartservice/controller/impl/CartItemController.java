package com.pawprints.cartservice.controller.impl;

import com.pawprints.cartservice.model.CartItem;

import java.util.List;

public interface CartItemController {

    public List<CartItem> findAllCartItems();
    public CartItem findCartItemById(Integer id);
    public List<CartItem> findAllCartItemsByCar(Integer cartId);
}
