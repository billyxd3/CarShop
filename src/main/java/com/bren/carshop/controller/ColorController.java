package com.bren.carshop.controller;

import com.bren.carshop.dto.request.ColorRequest;
import com.bren.carshop.dto.response.ColorResponse;
import com.bren.carshop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("color")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping
    public void save(@RequestBody ColorRequest request) {
        colorService.save(request);
    }

    @GetMapping
    public List<ColorResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return colorService.findAll(fieldName);
    }

    @GetMapping("/one/{id}")
    public ColorResponse findOne(@PathVariable Long id) {
        return colorService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<ColorResponse> findAllByName(@RequestParam String value) {
        return colorService.findAllByName(value);
    }

    @PutMapping
    public void update(@RequestBody ColorRequest request, Long id) {
        colorService.update(request,id);
    }

    @DeleteMapping
    public void delete(Long id) {
        colorService.delete(id);
    }
}
