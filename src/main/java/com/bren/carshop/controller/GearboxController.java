package com.bren.carshop.controller;

import com.bren.carshop.dto.request.GearboxRequest;
import com.bren.carshop.dto.response.GearboxResponse;
import com.bren.carshop.entity.Gearbox;
import com.bren.carshop.service.GearboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("gearbox")
public class GearboxController {

    @Autowired
    private GearboxService gearboxService;

    @PostMapping
    public void save(@RequestBody GearboxRequest request) {
        gearboxService.save(request);
    }

    @GetMapping
    public List<GearboxResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return gearboxService.findAll(fieldName);
    }

    @GetMapping("/one/{id}")
    public GearboxResponse findOne(@PathVariable Long id) {
        return gearboxService.findOneResponse(id);
    }

    @GetMapping("/byName")
    public List<GearboxResponse> findAllByName(@RequestParam String value) {
        return gearboxService.findAllByName(value);
    }

    @PutMapping
    public void update(@RequestBody GearboxRequest request, Long id) {
        gearboxService.update(request,id);
    }

    @DeleteMapping
    public void delete(Long id) {
        gearboxService.delete(id);
    }
}
