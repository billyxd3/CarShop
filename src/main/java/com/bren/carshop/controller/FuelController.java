package com.bren.carshop.controller;

import com.bren.carshop.dto.request.FuelRequest;
import com.bren.carshop.dto.response.FuelResponse;
import com.bren.carshop.service.FuelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("fuel")
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping
    public void save(@RequestBody FuelRequest request) {
        fuelService.save(request);
    }

    @GetMapping
    public List<FuelResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return fuelService.findAll(fieldName);
    }

    @GetMapping("/one/{id}")
    public FuelResponse findOne(@PathVariable Long id) {
        return fuelService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<FuelResponse> findAllByName(@RequestParam String value) {
        return fuelService.findAllByName(value);
    }

    @PutMapping
    public void update(@RequestBody FuelRequest request, Long id) {
        fuelService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        fuelService.delete(id);
    }
}
