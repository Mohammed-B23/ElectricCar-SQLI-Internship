package com.sue.cars.service;

import com.sue.cars.dtos.NeighborhoodDTO;

import java.util.List;
import java.util.Optional;

public interface NeighborhoodService extends ServiceCRUD{
    NeighborhoodDTO getNeighborhoodByPostalCode(Integer postalCode);
}
