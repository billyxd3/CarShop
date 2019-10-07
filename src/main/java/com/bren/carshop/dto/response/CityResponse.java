package com.bren.carshop.dto.response;

import com.bren.carshop.entity.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private Long id;
    private String name;

    public CityResponse(City city) {
        if (city == null) {
            city = new City();
        }
        id = city.getId();
        name = city.getName();
    }
}
