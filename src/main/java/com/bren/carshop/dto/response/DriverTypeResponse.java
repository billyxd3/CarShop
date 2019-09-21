package com.bren.carshop.dto.response;

import com.bren.carshop.entity.DriverType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverTypeResponse {

    private Long id;
    private String name;

    public DriverTypeResponse(DriverType driverType) {
        if (driverType == null) {
            driverType = new DriverType();
        }
        id = driverType.getId();
        name = driverType.getName();
    }
}
