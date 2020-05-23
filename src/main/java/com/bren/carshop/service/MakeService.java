package com.bren.carshop.service;

import com.bren.carshop.dto.request.MakeRequest;
import com.bren.carshop.dto.response.MakeResponse;
import com.bren.carshop.entity.Make;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.MakeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakeService {

    private final MakeRepository makeRepository;

    private final CountryService countryService;

    public MakeService(MakeRepository makeRepository, CountryService countryService) {
        this.makeRepository = makeRepository;
        this.countryService = countryService;
    }

    public void save(MakeRequest request) {
        makeRepository.save(makeRequestToMake(null, request));
    }

    public void update(MakeRequest request, Long id) {
        makeRepository.save(makeRequestToMake(findOne(id), request));

    }

    public void delete(Long id) {
        Make make = findOne(id);
        if (make.getModels().isEmpty()) {
            makeRepository.delete(make);
        } else {
            throw new HasDependenciesException("Can`t delete make with id " + id + " because it has dependencies");
        }
    }

    public List<MakeResponse> findAll(String fieldName) {
        return makeRepository.findAll(Sort.by(fieldName)).stream()
                .map(MakeResponse::new).collect(Collectors.toList());
    }

    public List<MakeResponse> findAllByCountryId(Long countryId) {
        return makeRepository.findAllByCountryId(countryId).stream().map(MakeResponse::new).collect(Collectors.toList());
    }

    public Make findOne(Long id) {
        return makeRepository.findById(id)
                .orElseThrow(() -> new NoMatchesException("Model with id " + id + " doesn`t exist"));
    }

    private Make makeRequestToMake(Make make, MakeRequest request) {
        if (make == null) {
            make = new Make();
        }
        make.setName(request.getName());
        make.setCountry(countryService.findOne(request.getCountryId()));
        return make;
    }

    public List<MakeResponse> findAllByName(String value) {
        return makeRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(MakeResponse::new).collect(Collectors.toList());
    }

    public MakeResponse findOneResponse(Long id) {
        return new MakeResponse(findOne(id));
    }
}
