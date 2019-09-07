package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Make;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeResponse {

    private Long id;
    private String name;

    public MakeResponse(Make make) {
        id = make.getId();
        name = make.getName();
    }
}
