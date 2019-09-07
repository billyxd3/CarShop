package com.bren.carshop.service;

import com.bren.carshop.dto.request.CarRequest;
import com.bren.carshop.dto.response.CarResponse;
import com.bren.carshop.dto.response.PageResponse;
import com.bren.carshop.entity.Car;
import com.bren.carshop.entity.Model;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private FileService fileService;

//    public void save(CarRequest request) {
//        Car car = new Car();
//        car.setPrice(request.getPrice());
//        car.setVolume(request.getVolume());
//        car.setYear(request.getYear());
//        Model model =  modelService.findOne(request.getModelId());
//        car.setModel(model);
//        carRepository.save(car);
//    }
    public void save(CarRequest request) throws IOException {
        carRepository.save(carRequestToCar(null,request));
    }

    public List<CarResponse> findAll() {
//        List<Car> all = carRepository.findAll();
//        List<CarResponse> responses = new ArrayList<>();
//        for (Car car : carRepository.findAll()) {
//            responses.add(new CarResponse(car));
//        }
//        return responses;
        return carRepository.findAll().stream()
                .map(CarResponse::new).collect(Collectors.toList());
    }

    public void update(CarRequest request, Long id) throws IOException {
        carRepository.save(carRequestToCar(findOne(id),request));
    }

    public void delete(CarRequest request, Long id) throws IOException {
        carRepository.delete(carRequestToCar(findOne(id),request));
    }

    private Car findOne(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NoMatchesException("Car with id" + id + "doesn`t exists"));
    }

    private Car carRequestToCar(Car car, CarRequest request) throws IOException {
        if (car == null) {
            car = new Car();
        }
        if (request.getPhoto() != null) {
            car.setPhoto(fileService.saveFile(request.getPhoto()));
        }
        car.setPrice(request.getPrice());
        car.setVolume(request.getVolume());
        car.setYear(request.getYear());
        Model model =  modelService.findOne(request.getModelId());
        car.setModel(model);
        car.setDescription(request.getDescription());
        return car;
    }

    public PageResponse<CarResponse> findPage(Integer page, Integer size, String fieldName, Sort.Direction direction) {
        Page<Car> data = carRepository.findAll(PageRequest.of(page,size, direction, fieldName));
        List<CarResponse> collect = data.get().map(CarResponse::new).collect(Collectors.toList());
        return new PageResponse<>(data.getTotalElements(),
                data.getTotalPages(),
                collect);
    }

}
