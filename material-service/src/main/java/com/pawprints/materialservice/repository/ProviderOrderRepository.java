package com.pawprints.materialservice.repository;

import com.pawprints.materialservice.model.ProviderOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProviderOrderRepository extends JpaRepository<ProviderOrder, Integer> {

    @Query(value = "SELECT o.status, COUNT(o.status) FROM provider_order o GROUP BY o.status", nativeQuery = true)
    public List<Object[]> countAllProviderOrdersByStatus();

}
