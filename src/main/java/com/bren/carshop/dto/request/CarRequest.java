package com.bren.carshop.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CarRequest {

    @NotNull
    private Integer year;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    private Double volume;

    @NotNull
    private Double rating;

    private String description;

    @NotNull
    private Long modelId;

    private Long bodyTypeId;

    private Long cityId;

    private Long colorId;

    private Long driverTypeId;

    private Long fuelId;

    private Long gearBoxId;

    private String photo;

    private String phoneNumber;

    private Boolean carConditionNew;

    private Integer power;

    private Boolean abs;

    private Boolean leatherSeats;


}
