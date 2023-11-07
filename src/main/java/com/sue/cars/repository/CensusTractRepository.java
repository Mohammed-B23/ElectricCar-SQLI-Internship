package com.sue.cars.repository;

import com.sue.cars.entity.CensusTract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CensusTractRepository extends JpaRepository<CensusTract,Long> {
    Optional<CensusTract> findCensusTractByCensusTract2020(Long census);
}
