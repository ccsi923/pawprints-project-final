package com.pawprints.materialservice.service;

import com.pawprints.materialservice.exceptions.NoPossibleUpdateOrder;
import com.pawprints.materialservice.exceptions.StockException;
import com.pawprints.materialservice.exceptions.NotFoundException;
import com.pawprints.materialservice.model.Order;
import com.pawprints.materialservice.model.OrderLine;
import com.pawprints.materialservice.model.Product;
import com.pawprints.materialservice.model.dto.OrderRequest;
import com.pawprints.materialservice.model.enums.OrderStatus;
import com.pawprints.materialservice.repository.OrderLineRepository;
import com.pawprints.materialservice.repository.OrderRepository;
import com.pawprints.materialservice.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;


    public List<Order> findAll(){
        return orderRepository.findAll();
    }


    public Order create(OrderRequest orderRequest){
        LOGGER.info("INIT - create");
        List<OrderLine> stockOrderLineList = new ArrayList<OrderLine>();
        Order order = new Order(orderRequest.getComments());
        orderRepository.save(order);

        orderRequest.getOrderLineRequests().forEach(orderLineRequest -> {

            OrderLine orderLine = new OrderLine(orderLineRequest.getProductCartType(),
                    orderLineRequest.getRequestedQuantity(), order);
            orderLineRepository.save(orderLine);
            stockOrderLineList.add(orderLine);

        });

        order.setStockOrderLineList(stockOrderLineList);
        LOGGER.info("END - create");
        return orderRepository.save(order);
    }

    public void updateOrderStatus(Integer id, OrderStatus orderStatus){
        LOGGER.info("INIT - updateOrderStatus");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
        order.setStatus(orderStatus);
        orderRepository.save(order);
        LOGGER.info("END - updateOrderStatus");
    }

    public List<Object[]> countAllOrdersByStatus(){
        return orderRepository.countAllOrdersByStatus();
    }
}
