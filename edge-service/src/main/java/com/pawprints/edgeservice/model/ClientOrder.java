package com.pawprints.edgeservice.model;

import com.pawprints.edgeservice.model.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClientOrder {

    private Integer id;
    private OrderStatus status;
    @Size(max = 255)
    private String comments;
    private List<OrderLine> stockOrderLineList = new ArrayList<>();

}
