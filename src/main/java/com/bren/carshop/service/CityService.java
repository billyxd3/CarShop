package com.bren.carshop.service;

import com.bren.carshop.dto.request.CityRequest;
import com.bren.carshop.dto.response.CityResponse;
import com.bren.carshop.entity.City;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.CityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void save(CityRequest request) {
        cityRepository.save(cityRequestToCity(null, request));
    }

    public void update(CityRequest request, Long id) {
        cityRepository.save(cityRequestToCity(findOne(id), request));
    }

    public List<CityResponse> findAll(String fieldName) {
        return cityRepository.findAll(Sort.by(fieldName)).stream()
                .map(CityResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        City city = findOne(id);
        if (city.getCars().isEmpty()) {
            cityRepository.delete(city);
        } else {
            throw new HasDependenciesException("Can`t delete city with id " + id + " because it has dependencies");
        }
    }

    public City findOne(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new NoMatchesException("City with id" + id + "doesn`t exists"));
    }

    public City cityRequestToCity(City city, CityRequest request) {
        if (city == null) {
            city = new City();
        }
        city.setName(request.getName());
        return city;
    }

    public List<CityResponse> findAllByName(String value) {
        return cityRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(CityResponse::new).collect(Collectors.toList());
    }

    public CityResponse findOneResponse(Long id) {
        return new CityResponse(findOne(id));
    }


}
