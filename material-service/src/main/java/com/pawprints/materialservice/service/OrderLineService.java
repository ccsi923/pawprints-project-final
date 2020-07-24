package com.pawprints.materialservice.service;

import com.pawprints.materialservice.exceptions.NotFoundException;
import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.repository.OrderLineRepository;
import com.pawprints.materialservice.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private static final Logger LOGGER = LogManager.getLogger(OrderLineService.class);


    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderLine> findByAllId(Integer ordId){
        LOGGER.info("INIT - findByAllId");
        Order order = orderRepository.findById(ordId).orElseThrow(()-> new NotFoundException("Order with id " + ordId +
                " not found"));
        LOGGER.info("END - findByAllId");

        return orderLineRepository.findAllByOrder(order);
    }

    public List<Object[]> sumQuantitiesByProductType(){
        LOGGER.info("INIT - sumQuantitiesByProductType");
        LOGGER.info("END - sumQuantitiesByProductType");
        return orderLineRepository.sumQuantitiesByProductType();
    }

}
