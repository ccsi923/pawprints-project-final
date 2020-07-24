package com.pawprints.edgeservice.model;

import com.pawprints.edgeservice.model.enums.OrderStatus;
import com.pawprints.edgeservice.model.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    private Integer id;
    private BigDecimal total;
    private LocalDateTime orderDate;
    private Integer userId;
    private Integer animalId;

    private PaymentType payment;

    private OrderStatus statusOrder;

}
