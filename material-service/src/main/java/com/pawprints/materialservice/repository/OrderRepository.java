package com.pawprints.materialservice.repository;

import com.pawprints.materialservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o.status, COUNT(o.status) FROM orders o GROUP BY o.status", nativeQuery = true)
    public List<Object[]> countAllOrdersByStatus();

}
