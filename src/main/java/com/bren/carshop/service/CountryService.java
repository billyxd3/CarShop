package com.bren.carshop.service;

import com.bren.carshop.dto.request.CountryRequest;
import com.bren.carshop.dto.response.CountryResponse;
import com.bren.carshop.entity.Country;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void save(CountryRequest request) {
        countryRepository.save(countryRequestToCountry(null,request));
    }

    public void update(CountryRequest request, Long id) {
        countryRepository.save(countryRequestToCountry(findOne(id),request));
    }

    public CountryResponse findOneResponse(Long id) {
        return new CountryResponse(findOne(id));
    }

    public Country findOne(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new NoMatchesException(" with id " + id + " doesn`t exists"));
    }

//    public Country findByIdAndName(Long id, String value) {
//        return countryRepository.findAllByIdAndName(id,value);
//    }

    private Country countryRequestToCountry(Country country, CountryRequest request) {
        if (country == null) {
            country = new Country();
        }
        country.setName(request.getName());
        return country;
    }

    public void delete(Long id) {
        Country country = findOne(id);
        if (country.getMakes().isEmpty()) {
            countryRepository.delete(country);
        } else {
           throw new HasDependenciesException("Can`t delete country with id \" + id + \" because it has dependencies");
        }
    }

    public List<CountryResponse> findAll(String fieldName) {
        return countryRepository.findAll(Sort.by(fieldName)).stream()
                .map(CountryResponse::new).collect(Collectors.toList());
    }

    public List<CountryResponse> findAllByName(String value) {
        return countryRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(CountryResponse::new).collect(Collectors.toList());
    }


}

