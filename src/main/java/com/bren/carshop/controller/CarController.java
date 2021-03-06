package com.bren.carshop.controller;

import com.bren.carshop.dto.request.CarCriteriaRequest;
import com.bren.carshop.dto.request.CarRequest;
import com.bren.carshop.dto.response.CarResponse;
import com.bren.carshop.dto.response.PageResponse;
import com.bren.carshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping
    public HttpStatus save(@Valid @RequestBody CarRequest request) throws IOException {
        carService.save(request);
        return HttpStatus.CREATED;
    }

    @GetMapping
    public PageResponse<CarResponse> findPage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(defaultValue = "id") String fieldId,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return carService.findPage(page, size, fieldId, direction);
    }

    @GetMapping("/filter")
    public PageResponse<CarResponse> findPageByCriteria(
            CarCriteriaRequest carCriteriaRequest,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(defaultValue = "id") String fieldId,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return carService.findPageByCriteria(carCriteriaRequest, page, size, fieldId, direction);
    }

    @GetMapping("/one/{id}")
    public CarResponse findOne(@PathVariable Long id) {
        return carService.findOneResponse(id);
    }

    @PutMapping
    public HttpStatus update(@Valid @RequestBody CarRequest request, Long id) throws IOException {
        carService.update(request, id);
        return HttpStatus.OK;
    }

    @DeleteMapping
    public HttpStatus delete(Long id) {
        carService.delete(id);
        return HttpStatus.OK;

    }




//    @GetMapping
//    public PageResponse<CarResponse> findPage(@Valid PaginationRequest paginationRequest) {
//
//    }


//    @GetMapping
//    public List<CarResponse> findAll() {
//        return carService.findAll();
//    }

}
