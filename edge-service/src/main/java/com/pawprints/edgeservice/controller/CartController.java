package com.pawprints.edgeservice.controller;


import com.pawprints.edgeservice.controller.dto.CartRequest;
import com.pawprints.edgeservice.controller.dto.OrderStatusRequest;
import com.pawprints.edgeservice.model.Cart;
import com.pawprints.edgeservice.model.CartItem;
import com.pawprints.edgeservice.model.Order;
import com.pawprints.edgeservice.model.User;
import com.pawprints.edgeservice.model.enums.OrderStatus;
import com.pawprints.edgeservice.service.CartService;
import com.pawprints.edgeservice.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Cart Controller")
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    @ApiOperation(value="Create a cart",
            notes = "Create a cart and a order",
            response = Cart.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCart(@AuthenticationPrincipal User user, @RequestBody CartRequest cartRequest){
        return cartService.createCart(user,cartRequest);
    }

    @GetMapping("/carts")
    @ApiOperation(value="Find all carts",
            notes = "Find all carts ",
            response = Cart.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Cart> findAllCarts(){
        return cartService.findAllCarts();
    }


    @GetMapping("/cartitems")
    @ApiOperation(value="Find all cart items",
            notes = "Find all cart items ",
            response = CartItem.class)
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllCartItems(){
        return cartService.findAllCartItems();
    }
    @GetMapping("/cartitem/{id}")
    @ApiOperation(value="Find cart item by id",
            notes = "Find cart item by id ",
            response = CartItem.class)
    @ResponseStatus(HttpStatus.OK)
    public CartItem findCartItemById(@PathVariable("id") Integer itemId){
        return cartService.findCartItemById(itemId);
    }

    @GetMapping("/cartitem/cart/{cartid}")
    @ApiOperation(value="Find all cart items by cart id",
            notes = "Find all cart items by cart id ",
            response = CartItem.class)
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> findAllCartItemsByCar(@PathVariable("cartid") Integer cartId){
        return cartService.findAllCartItemsByCar(cartId);
    }

    @GetMapping("/client/orders")
    @ApiOperation(value="Find all orders",
            notes = "Find all orders ",
            response = Order.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders(){
        return cartService.findAllOrders();
    }

    @GetMapping("/order/{cusdid}")
    @ApiOperation(value="Find all orders by customer id",
            notes = "Find all orders by customer id",
            response = Order.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findOrdersByCustomerId(@PathVariable("cusdid") Integer id){
        return cartService.findOrdersByCustomerId(id);
    }

    @PutMapping("/order/update/status/{ordid}")
    @ApiOperation(value="Update order status",
            notes = "Update order status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable("ordid") Integer id, @RequestBody OrderStatusRequest orderStatusRequest){
        cartService.updateOrderStatus(id, orderStatusRequest);
    }

    @GetMapping("/incomes/perday")
    @ApiOperation(value="Retrieve incomes per date",
            notes = "Retrieve incomes per date",
            response = Object[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> incomesPerDate(){
        return cartService.incomesPerDate();
    }


    @GetMapping("/carts/status/{status}")
    @ApiOperation(value="Find cart by status",
            notes = "Find cart by status",
            response = Order.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findCartsByStatus(@PathVariable("status") OrderStatus cartStatus){
        return cartService.findCartsByStatus(cartStatus);
    }
}
