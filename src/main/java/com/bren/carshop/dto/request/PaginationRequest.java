package com.bren.carshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

@Setter
@Getter
public class PaginationRequest {

    CarCriteriaRequest carCriteriaRequest;
    Integer page;
    Integer size;
    String fieldId;
    Sort.Direction direction;

}
