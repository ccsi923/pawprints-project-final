package com.pawprints.cartservice.repository;

import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findAllByCart(Cart cart);
}
