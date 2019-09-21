package com.bren.carshop.repository;

import com.bren.carshop.entity.BodyType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
    List<BodyType> findAllByNameLike(String name, Sort sort);
}
