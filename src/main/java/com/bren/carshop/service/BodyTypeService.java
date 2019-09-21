package com.bren.carshop.service;

import com.bren.carshop.dto.request.BodyTypeRequest;
import com.bren.carshop.dto.response.BodyTypeResponse;
import com.bren.carshop.entity.BodyType;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BodyTypeService {

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    public void save(BodyTypeRequest request) {
        bodyTypeRepository.save(bodyTypeRequestToBodyType(null,request));
    }

    public void update(BodyTypeRequest request, Long id) {
        bodyTypeRepository.save(bodyTypeRequestToBodyType(findOne(id),request));
    }

    public List<BodyTypeResponse> findAll(String fieldName) {
        return bodyTypeRepository.findAll(Sort.by(fieldName)).stream()
                .map(BodyTypeResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        BodyType bodyType = findOne(id);
        if (bodyType.getCars().isEmpty()) {
            bodyTypeRepository.delete(bodyType);
        } else {
            throw new HasDependenciesException("Can`t delete body type with id " + id + " because it has dependencies");
        }
    }

    public BodyType findOne(Long id) {
       return bodyTypeRepository.findById(id).orElseThrow(() -> new NoMatchesException("Body type with id" + id + "doesn`t exists"));
    }

    public BodyType bodyTypeRequestToBodyType(BodyType bodyType, BodyTypeRequest request) {
        if (bodyType == null) {
            bodyType = new BodyType();
        }
        bodyType.setName(request.getName());
        return bodyType;
    }

    public List<BodyTypeResponse> findAllByName(String value) {
        return bodyTypeRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(BodyTypeResponse::new).collect(Collectors.toList());
    }

    public BodyTypeResponse findOneResponse(Long id) {
        return new BodyTypeResponse(findOne(id));
    }
}
