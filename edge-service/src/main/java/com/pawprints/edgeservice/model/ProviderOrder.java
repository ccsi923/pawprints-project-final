package com.pawprints.edgeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawprints.edgeservice.model.enums.OrderReceptionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProviderOrder {

    private Integer id;

    @Size(max = 255)
    private String comments;
    private OrderReceptionStatus status;

}
