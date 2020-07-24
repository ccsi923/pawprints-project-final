package com.pawprints.edgeservice.client;

import com.pawprints.edgeservice.controller.dto.DateBetweenRequest;
import com.pawprints.edgeservice.controller.dto.OrderRequest;
import com.pawprints.edgeservice.controller.dto.ProviderOrderRequest;
import com.pawprints.edgeservice.model.*;
import com.pawprints.edgeservice.model.enums.OrderStatus;
import com.pawprints.edgeservice.model.enums.ProductType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="material-service", url = "https://material-pawprint-service.herokuapp.com/")
public interface MaterialClient {

    @GetMapping("/orders/by/status")
    public List<Object[]> countAllOrdersByStatus();

    @PutMapping("/update/order/{orderid}/status/{status}")
    public void updateOrderStatus(@PathVariable("orderid") Integer id, @PathVariable("status") OrderStatus orderStatus);

    @PostMapping("/order")
    public ClientOrder create(@RequestBody OrderRequest orderRequest);

    @GetMapping("/orders")
    public List<ClientOrder> findAllOrders();

    @GetMapping("/orderlines/by/order/{orderId}")
    public List<OrderLine> findAllOrderLineByOrderId(@PathVariable("orderId") Integer ordId);

    @GetMapping("/quantities/by/product/type")
    public List<Object[]> sumQuantitiesByProductType();

    @GetMapping("/product/{id}")
    public KitProduct findProductById(@PathVariable("id") Integer id);

    @GetMapping("/products")
    public List<KitProduct> findAllProducts();

    @PutMapping("/update/purchaseunits/by/order/{id}")
    public void updatePurchaseUnitsByOrder(@PathVariable("id") Integer orderId);

    @PutMapping("/makekit/by/producttype/{prodtype}/{quant}")
    public void makeKitByProductType(@PathVariable("prodtype") ProductType productType, @PathVariable("quant") Integer quantity);

    @GetMapping("/providerorders/by/status")
    public List<Object[]> countAllProviderOrdersByStatus();

    @GetMapping("/providerorders")
    public List<ProviderOrder> findAllProviderOrder();

    @PostMapping("/providerorder")
    public ProviderOrder createProviderOrder(@RequestBody ProviderOrderRequest providerOrderRequest);

    @GetMapping("/providerorderlines/by/providerorder/{probid}")
    public List<ProviderOrderLine> findAllProviderOrderLineByOrderId(@PathVariable("probid") Integer probId);

    @PutMapping("/expenses/between")
    public List<Object[]> expensesPerDateBetween(@RequestBody DateBetweenRequest dateBetweenRequest);

    @GetMapping("/expenses/per/date")
    public List<Object[]> expensesPerDate();
}
