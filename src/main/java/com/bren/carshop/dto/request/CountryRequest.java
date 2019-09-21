package com.bren.carshop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CountryRequest {

    @NotBlank
    private String name;
}
