package com.bren.carshop.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CarRequest {

    @NotNull
    private Integer year;
    @NotNull
    private Double volume;
    @NotNull
    @Positive
    private Long price;
    @NotBlank
    private String description;
    @NotNull
    private Long modelId;

    private String photo;


}
