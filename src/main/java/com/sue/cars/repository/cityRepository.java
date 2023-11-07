package com.sue.cars.repository;

import com.sue.cars.entity.Car;
import com.sue.cars.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface cityRepository extends JpaRepository<City,Long> {
    Optional<City> findByName(String name);
}
