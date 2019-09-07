package com.bren.carshop.service;

import com.bren.carshop.dto.request.CountryRequest;
import com.bren.carshop.entity.Country;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Country findOne(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new NoMatchesException(" with id " + id + " doesn`t exists"));
    }

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
}

