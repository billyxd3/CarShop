package com.bren.carshop.controller;

import com.bren.carshop.dto.request.MakeRequest;
import com.bren.carshop.dto.response.MakeResponse;
import com.bren.carshop.service.MakeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("make")
public class MakeController {

    private final MakeService makeService;

    public MakeController(MakeService makeService) {
        this.makeService = makeService;
    }

    @PostMapping
    public void save(@Valid @RequestBody MakeRequest request) {
        makeService.save(request);
    }

//    @GetMapping
//    public List<MakeResponse> findAll() {
//        return makeService.findAll();
//    }

    @GetMapping
    public List<MakeResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return makeService.findAll(fieldName);
    }

    @GetMapping("/byCountryId/{countryId}")
    public List<MakeResponse> findAllByCountryId(@PathVariable Long countryId) {
        return makeService.findAllByCountryId(countryId);
    }

    @GetMapping("/one/{id}")
    public MakeResponse findOne(@PathVariable Long id) {
        return makeService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<MakeResponse> findAllByName(@RequestParam String value) {
        return makeService.findAllByName(value);
    }

    @PutMapping
    public void update(@Valid @RequestBody MakeRequest request, Long id) {
        makeService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        makeService.delete(id);
    }


}
