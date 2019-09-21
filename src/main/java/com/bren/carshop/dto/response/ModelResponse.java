package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse {

    private Long id;
    private String name;
    private Long makeId;
    private MakeResponse makeResponse;

    public ModelResponse(Model model) {
        id = model.getId();
        name = model.getName();
        makeId = model.getMake().getId();
        makeResponse = new MakeResponse(model.getMake());
    }
}
