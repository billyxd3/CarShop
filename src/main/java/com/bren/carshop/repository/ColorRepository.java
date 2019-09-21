package com.bren.carshop.repository;

import com.bren.carshop.entity.Color;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    List<Color> findAllByNameLike(String name, Sort sort);
}
