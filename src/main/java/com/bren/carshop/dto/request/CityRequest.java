package com.bren.carshop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityRequest {

    private String name;

    private Long countryId;
}
