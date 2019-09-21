package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorResponse {

    private Long id;
    private String name;

    public ColorResponse(Color color) {
        if (color == null) {
            color = new Color();
        }
        id = color.getId();
        name = color.getName();
    }
}
