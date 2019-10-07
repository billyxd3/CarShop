package com.bren.carshop.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarCriteriaRequest {

    private Integer minYear;
    private Integer maxYear;
    private Integer minPrice;
    private Integer maxPrice;
    private Double minVolume;
    private Double maxVolume;
    private Boolean carConditionNew;
    private Boolean abs;
    private Boolean leatherSeats;
    private Integer minPower;
    private Integer maxPower;
    private Long bodyTypeId;
//    private Long bodyTypeId;
//    private List<Long> bodyTypeIds;
    private Long colorId;
//    private List<Long> colorIds;
//    private Long colorId;
    private Long driverTypeId;
//    private List<Long> driverTypeIds;
    private Long fuelId;
//    private List<Long> fuelIds;
    private Long gearboxId;
//    private List<Long> gearboxIds;
    private Long modelId;
    private Long makeId;
    private Long countryId;
    private Long cityId;
    private List<Long> favoriteCarsIds;
//    private List<Long> favoriteCarsIds;


}
