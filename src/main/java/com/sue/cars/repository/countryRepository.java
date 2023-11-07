package com.sue.cars.repository;

import com.sue.cars.entity.Car;
import com.sue.cars.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface countryRepository extends JpaRepository<Country,Long> {
    Optional<Country> findByName(String name);
}
