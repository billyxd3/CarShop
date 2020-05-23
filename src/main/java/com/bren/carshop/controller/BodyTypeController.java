package com.bren.carshop.controller;

import com.bren.carshop.dto.request.BodyTypeRequest;
import com.bren.carshop.dto.response.BodyTypeResponse;
import com.bren.carshop.service.BodyTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("bodyType")
public class BodyTypeController {

    private final BodyTypeService bodyTypeService;

    public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }

    @PostMapping
    public void save(@RequestBody BodyTypeRequest request) {
        bodyTypeService.save(request);
    }

    @GetMapping
    public List<BodyTypeResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return bodyTypeService.findAll(fieldName);
    }

    @GetMapping("/one/{id}")
    public BodyTypeResponse findOne(@PathVariable Long id) {
        return bodyTypeService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<BodyTypeResponse> findAllByName(@RequestParam String value) {
        return bodyTypeService.findAllByName(value);
    }

    @PutMapping
    public void update(@RequestBody BodyTypeRequest request, Long id) {
        bodyTypeService.update(request,id);
    }

    @DeleteMapping
    public void delete(Long id) {
        bodyTypeService.delete(id);
    }
}
