package com.sue.cars.service;

import com.sue.cars.dtos.CensusTractDTO;

public interface CensusTractService extends ServiceCRUD{
    CensusTractDTO getByCensusTract(Long census);
}
