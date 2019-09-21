package com.bren.carshop.repository;

import com.bren.carshop.entity.DriverType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverTypeRepository extends JpaRepository<DriverType, Long> {
    List<DriverType> findAllByNameLike(String name, Sort sort);
}
