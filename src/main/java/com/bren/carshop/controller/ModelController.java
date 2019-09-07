package com.bren.carshop.controller;

import com.bren.carshop.dto.request.ModelRequest;
import com.bren.carshop.dto.response.ModelResponse;
import com.bren.carshop.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public void save(@Valid @RequestBody ModelRequest request) {
        modelService.save(request);
    }

    @GetMapping
    public List<ModelResponse> findAll() {
        return modelService.findAll();
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
