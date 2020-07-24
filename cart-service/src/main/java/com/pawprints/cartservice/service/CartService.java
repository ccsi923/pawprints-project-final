package com.pawprints.cartservice.service;


import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.CartItem;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.model.Product;
import com.pawprints.cartservice.model.dto.CartRequest;
import com.pawprints.cartservice.model.enums.OrderStatus;
import com.pawprints.cartservice.repository.CartItemRepository;
import com.pawprints.cartservice.repository.CartRepository;
import com.pawprints.cartservice.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private static final Logger LOGGER = LogManager.getLogger(CartService.class);


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Cart create(CartRequest cartRequest) {
        LOGGER.info("INIT - create");

        Cart cart = new Cart();

        List<CartItem> cartItemList = new ArrayList<CartItem>();
        Order order = new Order(cart.getSubtotal(),
                cartRequest.getCustomerId(), cartRequest.getPaymentType(), cartRequest.getAnimalId());
        cart.setCustomerId(cartRequest.getCustomerId());
        cart.setPurchaseDate(LocalDate.now());
        Cart savedCart = cartRepository.save(cart);

        Order savedOrder = orderRepository.save(order);

        cartRequest.getProducts().forEach(productType -> {
            Product product = new Product(productType.getProductType(), productType.getRequestedQuantity());
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setOrder(order);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            cartItemList.add(cartItem);
        });
        savedCart.setCartItems(cartItemList);
        savedCart.setSubtotal(cart.calculateTotal());
        savedOrder.setCartItems(cartItemList);
        savedOrder.setTotal(cart.getSubtotal());
        orderRepository.save(savedOrder);
        LOGGER.info("Order saved");
        LOGGER.info("Cart caved");
        LOGGER.info("END - create");
        return cartRepository.save(savedCart);
    }

    public List<Cart> findAll() {
        LOGGER.info("INIT - findAll");
        LOGGER.info("END - findAll");
        return cartRepository.findAll();
    }

    public void deleteCart(Integer id) {
        LOGGER.info("INIT - deleteCart");
        LOGGER.info("END - deleteCart");
        cartRepository.deleteById(id);
    }


}
