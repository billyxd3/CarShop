package com.bren.carshop.service;

import com.bren.carshop.dto.request.GearboxRequest;
import com.bren.carshop.dto.response.GearboxResponse;
import com.bren.carshop.entity.Gearbox;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.GearboxRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GearboxService {

    private final GearboxRepository gearboxRepository;

    public GearboxService(GearboxRepository gearboxRepository) {
        this.gearboxRepository = gearboxRepository;
    }

    public void save(GearboxRequest request) {
        gearboxRepository.save(colorRequestToColor(null, request));
    }

    public void update(GearboxRequest request, Long id) {
        gearboxRepository.save(colorRequestToColor(findOne(id), request));
    }

    public List<GearboxResponse> findAll(String fieldName) {
        return gearboxRepository.findAll(Sort.by(fieldName)).stream()
                .map(GearboxResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Gearbox gearbox = findOne(id);
        if (gearbox.getCars().isEmpty()) {
            gearboxRepository.delete(gearbox);
        } else {
            throw new HasDependenciesException("Can`t delete gearbox with id " + id + " because it has dependencies");
        }
    }

    public Gearbox findOne(Long id) {
        return gearboxRepository.findById(id).orElseThrow(() -> new NoMatchesException("Gearbox with id" + id + "doesn`t exists"));
    }

    public Gearbox colorRequestToColor(Gearbox gearbox, GearboxRequest request) {
        if (gearbox == null) {
            gearbox = new Gearbox();
        }
        gearbox.setName(request.getName());
        return gearbox;
    }

    public List<GearboxResponse> findAllByName(String value) {
        return gearboxRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(GearboxResponse::new).collect(Collectors.toList());
    }

    public GearboxResponse findOneResponse(Long id) {
        return new GearboxResponse(findOne(id));
    }
}
