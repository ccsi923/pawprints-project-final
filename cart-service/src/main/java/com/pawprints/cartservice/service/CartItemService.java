package com.pawprints.cartservice.service;

import com.pawprints.cartservice.exceptions.NotFoundException;
import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.CartItem;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.repository.CartItemRepository;
import com.pawprints.cartservice.repository.CartRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private static final Logger LOGGER = LogManager.getLogger(CartItemService.class);

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<CartItem> findAll(){
        LOGGER.info("INIT - findAll");
        LOGGER.info("END - findAll");
        return cartItemRepository.findAll();
    }

    public CartItem findById(Integer id){
        LOGGER.info("INIT - findById");
        LOGGER.info("END - findById");
        return cartItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public List<CartItem> findAllByCar(Integer cartId){
        LOGGER.info("INIT - findAllByCar");
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart with id " +
                cartId + " not found"));
        LOGGER.info("Cart with id " + cart.getId()+ " found");
        LOGGER.info("END - findAllByCar");
        return cartItemRepository.findAllByCart(cart);

    }
}
