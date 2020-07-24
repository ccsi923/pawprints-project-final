package com.pawprints.edgeservice.controller.dto;
import com.pawprints.edgeservice.model.enums.AnimalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AnimalRequest {

    public Long clientId;
    @NotNull
    @Size(max = 20, min = 2)
    private String name;
    @NotNull
    private AnimalType animal;
    @Min(0)
    private Integer age;
}
