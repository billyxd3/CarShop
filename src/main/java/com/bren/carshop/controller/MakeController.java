package com.bren.carshop.controller;

import com.bren.carshop.dto.request.MakeRequest;
import com.bren.carshop.dto.response.MakeResponse;
import com.bren.carshop.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/make")
public class MakeController {

    @Autowired
    private MakeService makeService;

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

    @PutMapping
    public void update(@Valid @RequestBody MakeRequest request, Long id) {
        makeService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        makeService.delete(id);
    }




}
