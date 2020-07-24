package com.pawprints.cartservice.repository;

import com.pawprints.cartservice.model.Cart;
import com.pawprints.cartservice.model.Order;
import com.pawprints.cartservice.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT SUM(o.total), CAST(o.order_date AS DATE) FROM orders o GROUP BY CAST(o.order_date AS DATE)", nativeQuery = true)
    public List<Object[]> incomesPerDate();

    public List<Order> findAllByStatusOrder(OrderStatus status);

    public List<Order> findAllByUserId(Integer id);

}
