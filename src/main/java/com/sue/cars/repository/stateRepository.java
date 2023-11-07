package com.sue.cars.repository;

import com.sue.cars.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface stateRepository extends JpaRepository<State,Long> {
    Optional<State> findByName(String name);
}
