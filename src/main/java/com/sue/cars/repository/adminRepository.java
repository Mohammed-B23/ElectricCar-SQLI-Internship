package com.sue.cars.repository;

import com.sue.cars.entity.Admin;
import com.sue.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepository extends JpaRepository<Admin,Long> {
}
