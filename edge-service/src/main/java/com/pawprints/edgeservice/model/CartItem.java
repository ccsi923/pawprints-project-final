package com.pawprints.edgeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;

@Data
@NoArgsConstructor
public class CartItem {

    private Integer id;
    private Product product;

}
