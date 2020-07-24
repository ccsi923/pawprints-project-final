package com.pawprints.materialservice.service;

import com.pawprints.materialservice.exceptions.NotFoundException;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.ProviderOrder;
import com.pawprints.materialservice.model.ProviderOrderLine;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.dto.ProviderOrderRequest;
import com.pawprints.materialservice.repository.OrderLineRepository;
import com.pawprints.materialservice.repository.ProductRepository;
import com.pawprints.materialservice.repository.ProviderOrderLineRepository;
import com.pawprints.materialservice.repository.ProviderOrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProviderOrderService {

    private static final Logger LOGGER = LogManager.getLogger(ProviderOrderService.class);

    @Autowired
    private ProviderOrderRepository providerOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderOrderLineRepository providerOrderLineRepository;


    public List<ProviderOrder> findAll(){
        return providerOrderRepository.findAll();
    }


    public ProviderOrder create(ProviderOrderRequest providerOrderRequest){
        LOGGER.info("INIT - create");

        List<ProviderOrderLine> stockOrderLineList = new ArrayList<ProviderOrderLine>();

        ProviderOrder providerOrder = new ProviderOrder(providerOrderRequest.getComments());
        providerOrderRepository.save(providerOrder);

        providerOrderRequest.getProviderOrderLines().forEach(providerOrderLine -> {

            LOGGER.info("Looking for product with id " + providerOrderLine.getProductId());
            Product product = productRepository
                    .findById(providerOrderLine.getProductId())
                    .orElseThrow( () -> new NotFoundException("Product not found"));
            LOGGER.info("Product with id " + providerOrderLine.getProductId() + " found");

            product.setPurchaseUnits(product.getPurchaseUnits() +  providerOrderLine.getRequestedQuantity());

            LOGGER.info("Product with id " + providerOrderLine.getProductId() + " updated purchase units amount to "
                    + product.getPurchaseUnits() +  providerOrderLine.getRequestedQuantity());

            ProviderOrderLine newProviderOrderLine =
                    new ProviderOrderLine(product, providerOrderLine.getRequestedQuantity(), providerOrder);

            providerOrderLineRepository.save(newProviderOrderLine);
            productRepository.save(product);
            stockOrderLineList.add(newProviderOrderLine);
        });

        providerOrder.setStockOrderLineList(stockOrderLineList);
        LOGGER.info("END - create");
        return providerOrderRepository.save(providerOrder);

    }

    public List<Object[]> countAllProviderOrdersByStatus() {
        LOGGER.info("INIT - countAllProviderOrdersByStatus");
        LOGGER.info("END - countAllProviderOrdersByStatus");
        return providerOrderRepository.countAllProviderOrdersByStatus();
    }

}
