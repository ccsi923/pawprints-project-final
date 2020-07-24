package com.pawprints.materialservice.repository;

import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAllByType(ProductType productType);
}
