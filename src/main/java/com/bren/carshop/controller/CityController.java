package com.bren.carshop.controller;

import com.bren.carshop.dto.request.CityRequest;
import com.bren.carshop.dto.response.CityResponse;
import com.bren.carshop.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public void save(@RequestBody CityRequest request) {
        cityService.save(request);
    }

    @GetMapping
    public List<CityResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return cityService.findAll(fieldName);
    }

//    @GetMapping("/byCountryId/{countryId}")
//    public List<CityResponse> findAllByCountryId(@PathVariable Long countryId) {
//        return cityService.findAllByCountryId(countryId);
//    }

    @GetMapping("/one/{id}")
    public CityResponse findOne(@PathVariable Long id) {
        return cityService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<CityResponse> findAllByName(@RequestParam String value) {
        return cityService.findAllByName(value);
    }

    @PutMapping
    public void update(@RequestBody CityRequest request, Long id) {
        cityService.update(request,id);
    }

    @DeleteMapping
    public void delete(Long id) {
        cityService.delete(id);
    }
}
