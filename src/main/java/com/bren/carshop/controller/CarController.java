package com.bren.carshop.controller;

import com.bren.carshop.dto.request.CarRequest;
import com.bren.carshop.dto.response.CarResponse;
import com.bren.carshop.dto.response.PageResponse;
import com.bren.carshop.entity.Car;
import com.bren.carshop.service.CarService;
import io.swagger.models.auth.In;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public void save(@Valid @RequestBody CarRequest request) throws IOException {
        carService.save(request);
    }

//    @GetMapping
//    public List<CarResponse> findAll() {
//        return carService.findAll();
//    }

    @GetMapping
    public PageResponse<CarResponse> findPage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(defaultValue = "name") String fieldName,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return carService.findPage(page,size, fieldName, direction);
    }

    @PutMapping
    public void update(@Valid @RequestBody CarRequest request, Long id) throws IOException {
        carService.update(request,id);
    }

//    @DeleteMapping
//    public void delete(@RequestBody CarRequest request)

//    @GetMapping
//    public PageResponse<CarResponse> findPage(@Valid PaginationRequest paginationRequest) {
//
//    }

}
