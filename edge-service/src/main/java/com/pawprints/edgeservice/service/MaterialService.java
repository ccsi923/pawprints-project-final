package com.pawprints.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pawprints.edgeservice.client.CartClient;
import com.pawprints.edgeservice.client.MaterialClient;
import com.pawprints.edgeservice.controller.dto.DateBetweenRequest;
import com.pawprints.edgeservice.controller.dto.OrderRequest;
import com.pawprints.edgeservice.controller.dto.OrderStatusRequest;
import com.pawprints.edgeservice.controller.dto.ProviderOrderRequest;
import com.pawprints.edgeservice.exceptions.MaterialClientNotWorkingException;
import com.pawprints.edgeservice.exceptions.UserClientNotWorkingException;
import com.pawprints.edgeservice.model.*;
import com.pawprints.edgeservice.model.enums.OrderStatus;
import com.pawprints.edgeservice.model.enums.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    private static final Logger LOGGER = LogManager.getLogger(MaterialService.class);

    @Autowired
    private MaterialClient materialClient;

    @Autowired
    private CartClient cartClient;


    @HystrixCommand(fallbackMethod = "cantCountAllOrdersByStatus")
    public List<Object[]> countAllOrdersByStatus(){
        LOGGER.info("INIT - countAllOrdersByStatus");
        return materialClient.countAllOrdersByStatus();
    }

    public List<Object[]> cantCountAllOrdersByStatus() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantUpdateOrderStatus")
    public void updateOrderStatus(Integer id, OrderStatus orderStatus){
        LOGGER.info("INIT - updateOrderStatus");
        cartClient.updateOrderStatus(id, new OrderStatusRequest(orderStatus));
        LOGGER.info("Updated order in cart-service");
        materialClient.updateOrderStatus(id, orderStatus);
        LOGGER.info("Updated order in material-service");
        LOGGER.info("END - updateOrderStatus");

    }

    public void cantUpdateOrderStatus(Integer id, OrderStatus orderStatus) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllOrders")
    public List<ClientOrder> findAllOrders(){
        LOGGER.info("INIT - findAllOrders");
        return materialClient.findAllOrders();
    }

    public List<ClientOrder> cantFindAllOrders() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllOrderLineByOrderId")
    public List<OrderLine> findAllOrderLineByOrderId(Integer ordId){
        LOGGER.info("INIT - findAllOrderLineByOrderId");
        return materialClient.findAllOrderLineByOrderId(ordId);
    }

    public List<OrderLine> cantFindAllOrderLineByOrderId(Integer ordId) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantSumQuantitiesByProductType")
    public List<Object[]> sumQuantitiesByProductType(){
        LOGGER.info("INIT - sumQuantitiesByProductType");
        return materialClient.sumQuantitiesByProductType();
    }

    public List<Object[]> cantSumQuantitiesByProductType() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindProductById")
    public KitProduct findProductById(Integer id){
        LOGGER.info("INIT - findProductById");
        return materialClient.findProductById(id);
    }

    public KitProduct cantFindProductById(Integer id) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllProducts")
    public List<KitProduct> findAllProducts(){
        LOGGER.info("INIT - findAllProducts");

        return materialClient.findAllProducts();
    }

    public List<KitProduct> cantFindAllProducts() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantUpdatePurchaseUnitsByOrder")
    public void updatePurchaseUnitsByOrder(Integer orderId) {
        LOGGER.info("INIT - updatePurchaseUnitsByOrder");

        materialClient.updatePurchaseUnitsByOrder(orderId);
    }

    public void cantUpdatePurchaseUnitsByOrder(Integer orderId) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantMakeKitByProductType")
    public void makeKitByProductType(ProductType productType, Integer quantity){
        LOGGER.info("INIT - makeKitByProductType");

        materialClient.makeKitByProductType(productType, quantity);
    }

    public void cantMakeKitByProductType(ProductType productType, Integer quantity) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantCountAllProviderOrdersByStatus")
    public List<Object[]> countAllProviderOrdersByStatus() {
        LOGGER.info("INIT - countAllProviderOrdersByStatus");

        return materialClient.countAllProviderOrdersByStatus();
    }

    public List<Object[]> cantCountAllProviderOrdersByStatus() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllProviderOrder")
    public List<ProviderOrder> findAllProviderOrder(){
        LOGGER.info("INIT - findAllProviderOrder");
        return materialClient.findAllProviderOrder();
    }

    public List<ProviderOrder> cantFindAllProviderOrder() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantCreateProviderOrder")
    public ProviderOrder createProviderOrder(ProviderOrderRequest providerOrderRequest){
        LOGGER.info("INIT - createProviderOrder");
        return materialClient.createProviderOrder(providerOrderRequest);
    }

    public ProviderOrder cantCreateProviderOrder(ProviderOrderRequest providerOrderRequest) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantFindAllProviderOrderLineByOrderId")
    public List<ProviderOrderLine> findAllProviderOrderLineByOrderId(Integer probId){
        LOGGER.info("INIT - findAllProviderOrderLineByOrderId");

        return materialClient.findAllProviderOrderLineByOrderId(probId);
    }

    public List<ProviderOrderLine> cantFindAllProviderOrderLineByOrderId(Integer probId) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantExpensesPerDateBetween")
    public List<Object[]> expensesPerDateBetween(DateBetweenRequest dateBetweenRequest){
        LOGGER.info("INIT - expensesPerDateBetween");

        return materialClient.expensesPerDateBetween(dateBetweenRequest);
    }

    public List<Object[]> cantExpensesPerDateBetween(DateBetweenRequest dateBetweenRequest) {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }


    @HystrixCommand(fallbackMethod = "cantExpensesPerDate")
    public List<Object[]> expensesPerDate(){
        LOGGER.info("INIT - expensesPerDate");
        return materialClient.expensesPerDate();
    }

    public List<Object[]> cantExpensesPerDate() {
        LOGGER.error("material-service not available!");
        throw new MaterialClientNotWorkingException("material-service not available!");
    }
}
