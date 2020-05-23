package com.bren.carshop.controller;

import com.bren.carshop.dto.request.ModelRequest;
import com.bren.carshop.dto.response.ModelResponse;
import com.bren.carshop.service.ModelService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public void save(@Valid @RequestBody ModelRequest request) {
        modelService.save(request);
    }

    @GetMapping
    public List<ModelResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return modelService.findAll(fieldName);
    }

    @GetMapping("/byMakeId/{makeId}")
    public List<ModelResponse> findAllByCountryId(@PathVariable Long makeId) {
        return modelService.findAllByMakeId(makeId);
    }

    @GetMapping("/one/{id}")
    public ModelResponse findOne(@PathVariable Long id) {
        return modelService.findOneResponse(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody ModelRequest request, Long id) {
        modelService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        modelService.delete(id);
    }
}
