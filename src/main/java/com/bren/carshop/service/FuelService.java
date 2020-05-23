package com.bren.carshop.service;

import com.bren.carshop.dto.request.FuelRequest;
import com.bren.carshop.dto.response.FuelResponse;
import com.bren.carshop.entity.Fuel;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.FuelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelService {

    private final FuelRepository fuelRepository;

    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public void save(FuelRequest request) {
        fuelRepository.save(fuelRequestToFuel(null, request));
    }

    public void update(FuelRequest request, Long id) {
        fuelRepository.save(fuelRequestToFuel(findOne(id), request));
    }

    public List<FuelResponse> findAll(String fieldName) {
        return fuelRepository.findAll(Sort.by(fieldName)).stream()
                .map(FuelResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Fuel fuel = findOne(id);
        if (fuel.getCars().isEmpty()) {
            fuelRepository.delete(fuel);
        } else {
            throw new HasDependenciesException("Can`t delete fuel with id " + id + " because it has dependencies");
        }
    }

    public Fuel findOne(Long id) {
        return fuelRepository.findById(id).orElseThrow(() -> new NoMatchesException("Fuel with id" + id + "doesn`t exists"));
    }

    public Fuel fuelRequestToFuel(Fuel fuel, FuelRequest request) {
        if (fuel == null) {
            fuel = new Fuel();
        }
        fuel.setName(request.getName());
        return fuel;
    }

    public List<FuelResponse> findAllByName(String value) {
        return fuelRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(FuelResponse::new).collect(Collectors.toList());
    }

    public FuelResponse findOneResponse(Long id) {
        return new FuelResponse(findOne(id));
    }
}
