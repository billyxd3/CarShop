package com.bren.carshop.repository;

import com.bren.carshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByMakeId(Long makeId);
}
