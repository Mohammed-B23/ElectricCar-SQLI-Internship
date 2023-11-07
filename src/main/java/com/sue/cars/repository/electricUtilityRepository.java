package com.sue.cars.repository;

import com.sue.cars.entity.Car;
import com.sue.cars.entity.ElectricUtility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface electricUtilityRepository extends JpaRepository<ElectricUtility,Long> {
    Optional<ElectricUtility> findByName(String name);
}
