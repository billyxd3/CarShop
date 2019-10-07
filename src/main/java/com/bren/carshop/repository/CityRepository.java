package com.bren.carshop.repository;

import com.bren.carshop.entity.City;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
//    List<City> findAllByCountryId(Long countryId);

    List<City> findAllByNameLike(String name, Sort sort);
}
