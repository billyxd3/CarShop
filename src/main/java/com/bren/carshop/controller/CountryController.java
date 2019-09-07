package com.bren.carshop.controller;

import com.bren.carshop.dto.request.CountryRequest;
import com.bren.carshop.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public void save(@Valid @RequestBody CountryRequest request) {
        countryService.save(request);
    }

    @PutMapping
    public void update(@Valid @RequestBody CountryRequest request, Long id) {
        countryService.update(request,id);
    }

    @DeleteMapping
    public void delete(Long id) {
        countryService.delete(id);
    }


}
