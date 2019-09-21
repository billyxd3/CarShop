package com.bren.carshop.repository;

import com.bren.carshop.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor {

    @Query("from Car c join c.model m join m.make ma where ma.id=:makeId")
    List<Car> findAllByMakeId(@Param("makeId") Long makeId);
}
