package com.bren.carshop.controller;

import com.bren.carshop.dto.request.CountryRequest;
import com.bren.carshop.dto.response.CountryResponse;
import com.bren.carshop.service.CountryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public void save(@Valid @RequestBody CountryRequest request) {
        countryService.save(request);
    }

    @GetMapping
    public List<CountryResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return countryService.findAll(fieldName);
    }

    @GetMapping("/one/{id}")
    public CountryResponse findOne(@PathVariable Long id) {
        return countryService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<CountryResponse> findAllByName(@RequestParam String value) {
        return countryService.findAllByName(value);
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
