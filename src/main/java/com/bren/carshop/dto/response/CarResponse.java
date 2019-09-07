package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CarResponse {

    private Long id;
    private Integer year;
    private Double volume;
    private Long price;
    private String description;
    private String photo;

    public CarResponse(Car car) {
         id = car.getId();
         year = car.getYear();
         volume = car.getVolume();
         price = car.getPrice();
         description = car.getDescription();
         photo = car.getPhoto();
    }
}
