package com.pawprints.materialservice.repository;

import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    public List<OrderLine> findAllByOrder(Order order);

    @Query(value = "SELECT o.product_type, SUM(o.requested_quantity) FROM order_line o GROUP BY o.product_type", nativeQuery = true)
    public List<Object[]> sumQuantitiesByProductType();
}
