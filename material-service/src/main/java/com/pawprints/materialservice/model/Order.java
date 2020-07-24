package com.pawprints.materialservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawprints.materialservice.model.enums.OrderReceptionStatus;
import com.pawprints.materialservice.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Size(max = 255)
    private String comments;

    @OneToMany(mappedBy = "order")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<OrderLine> stockOrderLineList = new ArrayList<>();

    public Order(String comments) {
        this.comments = comments;
        this.status = OrderStatus.OPEN;
    }
}
