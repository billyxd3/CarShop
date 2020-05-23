package com.bren.carshop.service;

import com.bren.carshop.dto.request.DriverTypeRequest;
import com.bren.carshop.dto.response.DriverTypeResponse;
import com.bren.carshop.entity.DriverType;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.DriverTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverTypeService {

    private final DriverTypeRepository driverTypeRepository;

    public DriverTypeService(DriverTypeRepository driverTypeRepository) {
        this.driverTypeRepository = driverTypeRepository;
    }

    public void save(DriverTypeRequest request) {
        driverTypeRepository.save(driverTypeToDriverType(null, request));
    }

    public void update(DriverTypeRequest request, Long id) {
        driverTypeRepository.save(driverTypeToDriverType(findOne(id), request));
    }

    public List<DriverTypeResponse> findAll(String fieldName) {
        return driverTypeRepository.findAll(Sort.by(fieldName)).stream()
                .map(DriverTypeResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        DriverType driverType = findOne(id);
        if (driverType.getCars().isEmpty()) {
            driverTypeRepository.delete(driverType);
        } else {
            throw new HasDependenciesException("Can`t delete driver type with id " + id + " because it has dependencies");
        }
    }

    public DriverType findOne(Long id) {
        return driverTypeRepository.findById(id).orElseThrow(() -> new NoMatchesException("Driver type with id" + id + "doesn`t exists"));
    }

    public DriverType driverTypeToDriverType(DriverType driverType, DriverTypeRequest request) {
        if (driverType == null) {
            driverType = new DriverType();
        }
        driverType.setName(request.getName());
        return driverType;
    }

    public List<DriverTypeResponse> findAllByName(String value) {
        return driverTypeRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(DriverTypeResponse::new).collect(Collectors.toList());
    }

    public DriverTypeResponse findOneResponse(Long id) {
        return new DriverTypeResponse(findOne(id));
    }
}
