package com.pawprints.materialservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawprints.materialservice.model.enums.OrderReceptionStatus;
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

@Entity
@Data
@NoArgsConstructor
public class ProviderOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    private String comments;

    @JsonIgnore
    @OneToMany(mappedBy = "providerOrder")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<ProviderOrderLine> stockOrderLineList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderReceptionStatus status;


    public ProviderOrder(String comments) {
        this.comments = comments;
        this.status = OrderReceptionStatus.PENDING;
    }
}
