package com.pawprints.materialservice.repository;

import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.model.ProviderOrder;
import com.pawprints.materialservice.model.ProviderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProviderOrderLineRepository extends JpaRepository<ProviderOrderLine, Integer> {


    public List<ProviderOrderLine> findAllByProviderOrder(ProviderOrder providerOrder);

    @Query(value = "SELECT SUM(p.price),CAST(p.order_date AS DATE) FROM provider_order_line p WHERE p.order_date BETWEEN CAST(:date AS DATE) AND CAST(:date2 AS DATE) GROUP BY CAST(p.order_date AS DATE)", nativeQuery = true)
    public List<Object[]> expensesPerDateBetween(@Param("date") LocalDate date, @Param("date2") LocalDate date2);

    @Query(value = "SELECT SUM(p.price), CAST(p.order_date AS DATE) FROM provider_order_line p GROUP BY CAST(p.order_date AS DATE)", nativeQuery = true)
    public List<Object[]> expensesPerDate();
}
