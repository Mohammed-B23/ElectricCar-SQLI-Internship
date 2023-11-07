package com.sue.cars.repository;

import com.sue.cars.entity.LegislativeDistinct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LegislativeDistinctRepository extends JpaRepository<LegislativeDistinct, Long> {
    Optional<LegislativeDistinct> findLegislativeDistinctByLegislativeDistinct(Integer legislative);
}
