package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryResponse {

    private Long id;
    private String name;

    public CountryResponse(Country country) {
        id = country.getId();
        name = country.getName();
    }
}
