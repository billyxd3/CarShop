package com.bren.carshop.dto.response;

import com.bren.carshop.entity.BodyType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BodyTypeResponse {

    private Long id;
    private String name;

    public BodyTypeResponse(BodyType bodyType) {
        if (bodyType == null) {
            bodyType = new BodyType();
        }

        id = bodyType.getId();
        name = bodyType.getName();
    }
}
