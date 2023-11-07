package com.sue.cars.service;

import com.sue.cars.dtos.LegislativeDistinctDTO;

public interface LegislativeDistinctService extends ServiceCRUD{
    LegislativeDistinctDTO getByLegislative(Integer legislative);
}
