package com.pawprints.materialservice.controller;

import com.pawprints.materialservice.controller.impl.ProductController;
import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.enums.ProductType;
import com.pawprints.materialservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findProductById(@PathVariable("id") Integer id){
        return productService.findById(id);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @PutMapping("/update/purchaseunits/by/order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePurchaseUnitsByOrder(@PathVariable("id") Integer orderId) {
        productService.updatePurchaseUnitsByOrder(orderId);
    }

    @PutMapping("/makekit/by/producttype/{prodtype}/{quant}")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeKitByProductType(@PathVariable("prodtype") ProductType productType, @PathVariable("quant") Integer quantity){
        productService.makeKitByProductType(productType, quantity);
    }

}
