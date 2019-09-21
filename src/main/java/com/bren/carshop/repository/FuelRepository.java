package com.bren.carshop.repository;

import com.bren.carshop.entity.Fuel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {
    List<Fuel> findAllByNameLike(String name, Sort sort);
}
