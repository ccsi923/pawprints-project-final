package com.pawprints.edgeservice.controller;

import com.pawprints.edgeservice.controller.dto.DateBetweenRequest;
import com.pawprints.edgeservice.controller.dto.OrderRequest;
import com.pawprints.edgeservice.controller.dto.ProviderOrderRequest;
import com.pawprints.edgeservice.model.*;
import com.pawprints.edgeservice.model.enums.OrderStatus;
import com.pawprints.edgeservice.model.enums.ProductType;
import com.pawprints.edgeservice.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Material Controller")
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/orders/status")
    @ApiOperation(value="Count all orders by status",
            notes = "Count all orders by status",
            response = Object[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> countAllOrdersByStatus(){
        return materialService.countAllOrdersByStatus();
    }

    @PutMapping("/update/order/{orderid}/status/{status}")
    @ApiOperation(value="Update order status",
            notes = "Update order status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable("orderid") Integer id, @PathVariable("status") OrderStatus orderStatus){
        materialService.updateOrderStatus(id, orderStatus);
    }

    @GetMapping("/orders")
    @ApiOperation(value="Find all orders",
            notes = "Find all orders",
            response = ClientOrder.class)
    @ResponseStatus(HttpStatus.OK)
    public List<ClientOrder> findAllOrders(){
        return materialService.findAllOrders();
    }

    @GetMapping("/orderlines/order/{orderId}")
    @ApiOperation(value="Find all order line by order id",
            notes = "Find all order line by order id",
            response = OrderLine.class)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderLine> findAllOrderLineByOrderId(@PathVariable("orderId") Integer ordId){

        return materialService.findAllOrderLineByOrderId(ordId);
    }

    @GetMapping("/quantities/product/type")
    @ApiOperation(value="Calculate total of products sold",
            notes = "Calculate total of products sold grouped by product",
            response = Object[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> sumQuantitiesByProductType(){
        return materialService.sumQuantitiesByProductType();
    }

    @GetMapping("/product/{id}")
    @ApiOperation(value="Find product by id",
            notes = "Find product by id",
            response = KitProduct.class)
    @ResponseStatus(HttpStatus.OK)
    public KitProduct findProductById(@PathVariable("id") Integer id){
        return materialService.findProductById(id);
    }

    @GetMapping("/products")
    @ApiOperation(value="Find all products",
            notes = "Find all products",
            response = KitProduct.class)
    @ResponseStatus(HttpStatus.OK)
    public List<KitProduct> findAllProducts(){
        return materialService.findAllProducts();
    }

    @PutMapping("/update/purchaseunits/order/{id}")
    @ApiOperation(value="Update by provider order id",
            notes = "Update by provider order id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePurchaseUnitsByOrder(@PathVariable("id") Integer orderId) {
        materialService.updatePurchaseUnitsByOrder(orderId);
    }

    @PutMapping("/makekit/producttype/{prodtype}/{quant}")
    @ApiOperation(value="Make a kit by product type",
            notes = "Make a kit by product type")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeKitByProductType(@PathVariable("prodtype") ProductType productType, @PathVariable("quant") Integer quantity){
        materialService.makeKitByProductType(productType, quantity);
    }


    @GetMapping("/providerorders/status")
    @ApiOperation(value="All provider orders by status",
            notes = "All provider order by status",
            response = Object[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> countAllProviderOrdersByStatus() {
        return materialService.countAllProviderOrdersByStatus();
    }

    @GetMapping("/providerorders")
    @ApiOperation(value="Find all provider orders",
            notes = "Find all provider orders",
            response = ProviderOrder.class)
    @ResponseStatus(HttpStatus.OK)
    public List<ProviderOrder> findAllProviderOrder(){
        return materialService.findAllProviderOrder();
    }

    @PostMapping("/providerorder")
    @ApiOperation(value="Create a provider order",
            notes = "Create a provider order",
            response = ProviderOrder.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderOrder createProviderOrder(@RequestBody ProviderOrderRequest providerOrderRequest){
        return materialService.createProviderOrder(providerOrderRequest);
    }

    @GetMapping("/providerorderlines/providerorder/{probid}")
    @ApiOperation(value="Find provider order lines by order id",
            notes = "Find provider order lines by order id",
            response = ProviderOrderLine.class)
    @ResponseStatus(HttpStatus.OK)
    public List<ProviderOrderLine> findAllProviderOrderLineByOrderId(@PathVariable("probid") Integer probId){
        return materialService.findAllProviderOrderLineByOrderId(probId);
    }

    @PutMapping("/expenses/between")
    @ApiOperation(value="Retrieve expenses between two dates",
            notes = "Retrieve expenses between two dates",
            response = Object[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> expensesPerDateBetween(@RequestBody DateBetweenRequest dateBetweenRequest){
        return materialService.expensesPerDateBetween(dateBetweenRequest);
    }

    @GetMapping("/expenses/per/date")
    @ApiOperation(value="Retrieve expenses per date",
            notes = "Retrieve expenses per date",
            response = Object[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> expensesPerDate(){
        return materialService.expensesPerDate();
    }
}
