package com.sue.cars.repository;

import com.sue.cars.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
    Optional<Neighborhood> findNeighborhoodByPostalCode(Integer postalCode);
}
