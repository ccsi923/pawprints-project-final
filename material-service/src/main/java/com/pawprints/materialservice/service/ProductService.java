package com.pawprints.materialservice.service;

import com.pawprints.materialservice.exceptions.CantUpdatePurchaseUnit;
import com.pawprints.materialservice.exceptions.NoPossibleUpdateOrder;
import com.pawprints.materialservice.exceptions.NotFoundException;
import com.pawprints.materialservice.exceptions.StockException;
import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.ProviderOrder;
import com.pawprints.materialservice.model.enums.OrderReceptionStatus;
import com.pawprints.materialservice.model.enums.ProductType;
import com.pawprints.materialservice.repository.ProductRepository;
import com.pawprints.materialservice.repository.ProviderOrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderOrderRepository providerOrderRepository;

    public Product findById(Integer id){
        LOGGER.info("INTI - findById");
        LOGGER.info("END - findById");
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public List<Product> findAll(){
        LOGGER.info("INTI - findAll");
        LOGGER.info("END - findAll");
        return productRepository.findAll();
    }

    public void updatePurchaseUnitsByOrder(Integer orderId){

        LOGGER.info("INTI - updatePurchaseUnits");
        ProviderOrder order = providerOrderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found"));

        if (order.getStatus().equals(OrderReceptionStatus.FULL)) {
            LOGGER.error("Not possible to update order with id " + order.getId() + ", " +
                    "update was already done");
            throw new NoPossibleUpdateOrder("Not possible to update order with id " + order.getId() + ", " +
                    "update was already done");
        }

        order.getStockOrderLineList().forEach(orderLine -> {
            Product product = productRepository.findById(orderLine.getProduct()
                    .getId()).orElseThrow(() -> new NotFoundException("Product not found"));

            if(product.getPurchaseUnits() == 0){
                LOGGER.error("Purchase units is already 0");
                throw new CantUpdatePurchaseUnit("Purchase units is already 0");
            }

            product.setPurchaseUnits(product.getPurchaseUnits() - orderLine.getRequestedQuantity());
            product.setTotalRemainingUnits(product.getTotalRemainingUnits() + orderLine.getRequestedQuantity());
            productRepository.save(product);

        });
        order.setStatus(OrderReceptionStatus.FULL);
        providerOrderRepository.save(order);
        LOGGER.info("END - updatePurchaseUnits");

    }

    @Transactional
    public void makeKitByProductType(ProductType productType, Integer quantity){
        LOGGER.info("INTI - makeKitByProductType");

        List<Product> products = productRepository.findAllByType(productType);
        for (int i = 0; i < quantity; i++) {
            products.forEach(product -> {
                String name = product.getName().toLowerCase();
                switch (name) {

                    case "sample boxes":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 1);
                        LOGGER.info("sample boxes - 1");
                        return;
                    case "test sticks":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 2);
                        LOGGER.info("test sticks - 2");
                        return;

                    case "sterilized bags":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 3);
                        LOGGER.info("sterilized bags - 3");
                        return;

                    case "brochures":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 2);
                        LOGGER.info("brochures - 2");
                        return;

                    case "shipping boxes":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 1);
                        LOGGER.info("shipping boxes - 1");
                        return;

                    case "product brochure":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 2);
                        LOGGER.info("product brochure - 2");
                        return;

                    case "gift":
                        product.setTotalRemainingUnits(product.getTotalRemainingUnits() - 1);
                        LOGGER.info("gift - 1");
                        return;
                }

                if (product.getTotalRemainingUnits() < product.getMinStock()) {
                    LOGGER.error("Not possible to elaborate kit with product " + product.getName() + ", " +
                            "please make a new order, stock is near to minimum");
                    throw new StockException("Not possible to elaborate kit with product " + product.getName() + ", " +
                            "please make a new order, stock is near to minimum");
                }

            });
        }
        LOGGER.info("END - makeKitByProductType");
        productRepository.saveAll(products);

    }

}
