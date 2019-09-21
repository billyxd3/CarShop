package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Fuel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FuelResponse {

    private Long id;
    private String name;

    public FuelResponse(Fuel fuel) {
        if (fuel == null) {
            fuel = new Fuel();
        }
        id = fuel.getId();
        name = fuel.getName();
    }
}
