package com.bren.carshop.dto.response;

import com.bren.carshop.entity.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private Long id;
    private String name;
    private Long countryId;
    private CountryResponse countryResponse;

    public CityResponse(City city) {
        if (city == null) {
            city = new City();
        }
        id = city.getId();
        name = city.getName();
        countryId = city.getCountry().getId();
        countryResponse = new CountryResponse(city.getCountry());
    }
}
