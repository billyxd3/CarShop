package com.bren.carshop.controller;

import com.bren.carshop.dto.request.DriverTypeRequest;
import com.bren.carshop.dto.response.DriverTypeResponse;
import com.bren.carshop.service.DriverTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("driverType")
public class DriverTypeController {


    @Autowired
    private DriverTypeService driverTypeService;

    @PostMapping
    public void save(@RequestBody DriverTypeRequest request) {
        driverTypeService.save(request);
    }

    @GetMapping
    public List<DriverTypeResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return driverTypeService.findAll(fieldName);
    }

    @GetMapping("/one/{id}")
    public DriverTypeResponse findOne(@PathVariable Long id) {
        return driverTypeService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<DriverTypeResponse> findAllByName(@RequestParam String value) {
        return driverTypeService.findAllByName(value);
    }

    @PutMapping
    public void update(@RequestBody DriverTypeRequest request, Long id) {
        driverTypeService.update(request,id);
    }

    @DeleteMapping
    public void delete(Long id) {
        driverTypeService.delete(id);
    }
}
