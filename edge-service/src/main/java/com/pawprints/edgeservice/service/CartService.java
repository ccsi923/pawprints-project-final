package com.pawprints.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pawprints.edgeservice.client.CartClient;
import com.pawprints.edgeservice.client.MaterialClient;
import com.pawprints.edgeservice.controller.dto.CartRequest;
import com.pawprints.edgeservice.controller.dto.OrderLineRequest;
import com.pawprints.edgeservice.controller.dto.OrderRequest;
import com.pawprints.edgeservice.controller.dto.OrderStatusRequest;
import com.pawprints.edgeservice.exceptions.CartClientNotWorkingException;
import com.pawprints.edgeservice.exceptions.MaterialClientNotWorkingException;
import com.pawprints.edgeservice.model.Cart;
import com.pawprints.edgeservice.model.CartItem;
import com.pawprints.edgeservice.model.Order;
import com.pawprints.edgeservice.model.User;
import com.pawprints.edgeservice.model.enums.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final Logger LOGGER = LogManager.getLogger(CartService.class);

    @Autowired
    private CartClient cartClient;

    @Autowired
    private MaterialClient materialClient;

    @HystrixCommand(fallbackMethod = "cantCreateCart")
    public Cart createCart(User user , CartRequest cartRequest){
        LOGGER.info("INIT - createCart");
        cartRequest.setCustomerId(user.getId());
        System.out.println(cartRequest);
        List<OrderLineRequest> orderLineRequests = new ArrayList<>();

        cartRequest.getProducts().forEach(product -> {
            orderLineRequests.add(new OrderLineRequest(product.getProductType(),
                    product.getRequestedQuantity()));
        });

        materialClient.create(new OrderRequest(orderLineRequests, cartRequest.getComments()));
        return cartClient.createCart(cartRequest);
    }
    public Cart cantCreateCart(User user , CartRequest cartRequest) {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllCarts")
    public List<Cart> findAllCarts(){
        LOGGER.info("INIT - findAllCarts");
        return cartClient.findAllCarts();
    }

    public List<Cart> cantFindAllCarts() {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllCartItems")
    public List<CartItem> findAllCartItems(){
        LOGGER.info("INIT - findAllCartItems");
        return cartClient.findAllCartItems();
    }

    public List<CartItem>  cantFindAllCartItems() {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindCartItemById")
    public CartItem findCartItemById(Integer itemId){
        LOGGER.info("INIT - findCartItemById");

        return cartClient.findCartItemById(itemId);
    }

    public CartItem cantFindCartItemById(Integer itemId) {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }

    @HystrixCommand(fallbackMethod = "cantFindAllCartItemsByCar")
    public List<CartItem> findAllCartItemsByCar(Integer cartId){
        LOGGER.info("INIT - findAllCartItemsByCar");

        return cartClient.findAllCartItemsByCar(cartId);
    }

    public List<CartItem> cantFindAllCartItemsByCar(Integer cartId) {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllOrders")
    public List<Order> findAllOrders(){
        LOGGER.info("INIT - findAllOrders");

        return cartClient.findAllOrders();
    }

    public List<Order> cantFindAllOrders() {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindOrdersByCustomerId")
    public List<Order> findOrdersByCustomerId(Integer id){
        LOGGER.info("INIT - findOrdersByCustomerId");

        return cartClient.findOrdersByCustomerId(id);
    }

    public List<Order> cantFindOrdersByCustomerId(Integer id) {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantUpdateOrderStatus")
    public void updateOrderStatus(Integer id, OrderStatusRequest orderStatusRequest){
        LOGGER.info("INIT - updateOrderStatus");

        materialClient.updateOrderStatus(id, orderStatusRequest.getOrderStatus());
        LOGGER.info("Updated order in material-service");
        cartClient.updateOrderStatus(id, orderStatusRequest);
        LOGGER.info("Updated order in cart-service");
        LOGGER.info("END - updateOrderStatus");
    }
    public void cantUpdateOrderStatus(Integer id, OrderStatusRequest orderStatusRequest) {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantIncomesPerDate")
    public List<Object[]> incomesPerDate(){
        LOGGER.info("INIT - incomesPerDate");

        return cartClient.incomesPerDate();
    }
    public List<Object[]> cantIncomesPerDate() {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindCartsByStatus")
    public List<Order> findCartsByStatus(OrderStatus cartStatus){
        LOGGER.info("INIT - findCartsByStatus");

        return cartClient.findCartsByStatus(cartStatus);
    }

    public List<Order> cantFindCartsByStatus(OrderStatus cartStatus) {
        LOGGER.error("cart-service not available!");
        throw new CartClientNotWorkingException("cart-service not available!");
    }
}
