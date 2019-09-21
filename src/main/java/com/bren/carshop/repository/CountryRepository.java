package com.bren.carshop.repository;

import com.bren.carshop.entity.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAllByNameLike(String name, Sort sort);

//    Country findAllByIdAndName(Long id, String name);
}
