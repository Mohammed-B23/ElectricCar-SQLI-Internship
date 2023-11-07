package com.sue.cars.repository;

import com.sue.cars.entity.Car;
import com.sue.cars.entity.City;
import com.sue.cars.entity.ModelBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface carRepository extends JpaRepository<Car, Long> {
    Page<Car> findAllByVin(String vin, Pageable pageable);
    List<Car> findByVin(String vin);
    Optional<Car> findByDolVehicleId(Long dolVehicleId);
    List<Car> findCarByModelBrand(ModelBrand ModelBrand);
    @Query("select distinct c.vehicleLocation from Car c, City cy where cy.id = c.city.id and cy.id = ?1")
    List<String> findAllByCity(Long idCity);
}
