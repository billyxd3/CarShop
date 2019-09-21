package com.bren.carshop.repository;

import com.bren.carshop.entity.Country;
import com.bren.carshop.entity.Make;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {

    List<Make> findAllByCountryId(Long countryId);

    List<Make> findAllByNameLike(String name, Sort sort);
}
