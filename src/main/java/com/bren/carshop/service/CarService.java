package com.bren.carshop.service;

import com.bren.carshop.dto.request.CarCriteriaRequest;
import com.bren.carshop.dto.request.CarRequest;
import com.bren.carshop.dto.response.CarResponse;
import com.bren.carshop.dto.response.PageResponse;
import com.bren.carshop.entity.*;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.CarRepository;
import com.bren.carshop.specification.CarSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    private final ModelService modelService;

    private final BodyTypeService bodyTypeService;

    private final CityService cityService;

    private final ColorService colorService;

    private final DriverTypeService driverTypeService;

    private final FuelService fuelService;

    private final GearboxService gearboxService;

    private final FileService fileService;

    public CarService(CarRepository carRepository, ModelService modelService, BodyTypeService bodyTypeService, CityService cityService, ColorService colorService, DriverTypeService driverTypeService, FuelService fuelService, GearboxService gearboxService, FileService fileService) {
        this.carRepository = carRepository;
        this.modelService = modelService;
        this.bodyTypeService = bodyTypeService;
        this.cityService = cityService;
        this.colorService = colorService;
        this.driverTypeService = driverTypeService;
        this.fuelService = fuelService;
        this.gearboxService = gearboxService;
        this.fileService = fileService;
    }


    public void save(CarRequest request) throws IOException {
        carRepository.save(carRequestToCar(null, request));
    }

    public List<CarResponse> findAll() {
        return carRepository.findAll().stream()
                .map(CarResponse::new).collect(Collectors.toList());
    }

    public void update(CarRequest request, Long id) throws IOException {
        carRepository.save(carRequestToCar(findOne(id), request));
    }

    public void delete(Long id) {
        carRepository.delete(findOne(id));
    }

    private Car findOne(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NoMatchesException("Car with id" + id + "doesn`t exists"));
    }

    public CarResponse findOneResponse(Long id) {
        return new CarResponse(findOne(id));
    }

    private Car carRequestToCar(Car car, CarRequest request) throws IOException {
        if (car == null) {
            car = new Car();
        }
        if (request.getPhoto() != null) {
            car.setPhoto(fileService.saveFile(request.getPhoto()));
        }
        car.setPhoneNumber(request.getPhoneNumber());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
        car.setVolume(request.getVolume());
        car.setRating(request.getRating());
        Model model = modelService.findOne(request.getModelId());
        car.setModel(model);

        if (request.getBodyTypeId() != null) {
            car.setBodyType(bodyTypeService.findOne(request.getBodyTypeId()));
        }
        if (request.getCityId() != null) {
            car.setCity(cityService.findOne(request.getCityId()));
        }
        if (request.getColorId() != null) {
            car.setColor(colorService.findOne(request.getColorId()));
        }
        if (request.getDriverTypeId() != null) {
            car.setDriverType(driverTypeService.findOne(request.getDriverTypeId()));
        }
        if (request.getFuelId() != null) {
            car.setFuel(fuelService.findOne(request.getFuelId()));
        }
        if (request.getGearBoxId() != null) {
            car.setGearbox(gearboxService.findOne(request.getGearBoxId()));
        }
        car.setDescription(request.getDescription());
        car.setCarConditionNew(request.getCarConditionNew());
        car.setPower(request.getPower());
        car.setAbs(request.getAbs());
        car.setLeatherSeats(request.getLeatherSeats());
        return car;
    }

    public PageResponse<CarResponse> findPage(Integer page, Integer size, String fieldId, Sort.Direction direction) {
        Page<Car> data = carRepository.findAll(PageRequest.of(page, size, direction, fieldId));
        List<CarResponse> collect = data.get().map(CarResponse::new).collect(Collectors.toList());
        return new PageResponse<>(data.getTotalElements(),
                data.getTotalPages(),
                collect);
    }

    public PageResponse<CarResponse> findPageByCriteria(CarCriteriaRequest cr, Integer page, Integer size, String fieldId, Sort.Direction direction) {
        Page<Car> data = carRepository.findAll(new CarSpecification(cr), PageRequest.of(page, size, direction, fieldId));
        List<CarResponse> collect = data.get().map(CarResponse::new).collect(Collectors.toList());
        return new PageResponse<>(data.getTotalElements(),
                data.getTotalPages(),
                collect);
    }
}
