package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Gearbox;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GearboxResponse {

    private Long id;
    private String name;

    public GearboxResponse(Gearbox gearbox) {
        if (gearbox == null) {
            gearbox = new Gearbox();
        }
        id = gearbox.getId();
        name = gearbox.getName();
    }
}
