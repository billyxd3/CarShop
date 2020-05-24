package com.bren.carshop.dto.response;

import com.bren.carshop.entity.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CarResponse {

    private Long id;
    private Integer year;
    private Integer price;
    private Double volume;
    private Double rating;
    private String description;
    private String photo;
    private String phoneNumber;
    private Boolean carConditionNew;
    private Integer power;
    private Boolean abs;
    private Boolean leatherSeats;
    private ModelResponse modelResponse;
    private BodyTypeResponse bodyTypeResponse;
    private CityResponse cityResponse;
    private ColorResponse colorResponse;
    private DriverTypeResponse driverTypeResponse;
    private FuelResponse fuelResponse;
    private GearboxResponse gearboxResponse;


    public CarResponse(Car car) {
        id = car.getId();
        year = car.getYear();
        price = car.getPrice();
        volume = car.getVolume();
        rating = car.getRating();
        description = car.getDescription();
        carConditionNew = car.getCarConditionNew();
        power = car.getPower();
        abs = car.getAbs();
        leatherSeats = car.getLeatherSeats();
        photo = car.getPhoto();
        phoneNumber = car.getPhoneNumber();
        modelResponse = new ModelResponse(car.getModel());

        if (car.getBodyType() != null) {
            bodyTypeResponse = new BodyTypeResponse(car.getBodyType());
        }
        if (car.getCity() != null) {
            cityResponse = new CityResponse(car.getCity());
        }
        if (car.getColor() != null) {
            colorResponse = new ColorResponse(car.getColor());
        }
        if (car.getDriverType() != null) {
            driverTypeResponse = new DriverTypeResponse(car.getDriverType());
        }
        if (car.getFuel() != null) {
            fuelResponse = new FuelResponse(car.getFuel());
        }
        if (car.getGearbox() != null) {
            gearboxResponse = new GearboxResponse(car.getGearbox());
        }

    }
}
