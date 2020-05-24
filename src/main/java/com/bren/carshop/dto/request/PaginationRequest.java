package com.bren.carshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public class PaginationRequest {

    CarCriteriaRequest carCriteriaRequest;
    Integer page;
    Integer size;
    String fieldId;
    Sort.Direction direction;

}




