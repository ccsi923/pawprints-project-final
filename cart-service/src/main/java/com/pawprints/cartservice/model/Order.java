package com.pawprints.cartservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawprints.cartservice.model.enums.OrderStatus;
import com.pawprints.cartservice.model.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total", precision = 10)
    private BigDecimal total;

    private LocalDateTime orderDate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    private Integer userId;
    private Integer animalId;

    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus statusOrder;

    public Order(BigDecimal total,
                 Integer userId, PaymentType payment, Integer animalId) {
        this.total = total;
        this.orderDate = LocalDateTime.now();
        this.userId = userId;
        this.payment = payment;
        this.statusOrder = OrderStatus.OPEN;
        this.animalId = animalId;
    }

}
