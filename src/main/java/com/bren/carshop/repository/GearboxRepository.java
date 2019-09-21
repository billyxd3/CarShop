package com.bren.carshop.repository;

import com.bren.carshop.entity.Gearbox;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearboxRepository extends JpaRepository<Gearbox, Long> {
    List<Gearbox> findAllByNameLike(String name, Sort sort);
}
