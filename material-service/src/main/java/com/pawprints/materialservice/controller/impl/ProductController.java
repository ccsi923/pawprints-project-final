package com.pawprints.materialservice.controller.impl;

import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.enums.ProductType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductController {

    public Product findProductById(Integer id);
    public List<Product> findAllProducts();
    public void updatePurchaseUnitsByOrder(Integer orderId);
    public void makeKitByProductType(ProductType productType, Integer quantity);
}
