package com.sue.cars.repository;

import com.sue.cars.entity.Brand;
import com.sue.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface brandRepository extends JpaRepository<Brand,Long> {
    Optional<Brand> findByName(String name);
    void deleteByName(String name);
}
