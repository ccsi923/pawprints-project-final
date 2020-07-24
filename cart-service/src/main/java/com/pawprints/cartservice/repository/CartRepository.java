package com.pawprints.cartservice.repository;

import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
