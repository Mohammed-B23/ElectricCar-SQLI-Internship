package com.sue.cars.service;

import com.sue.cars.dtos.ElectricUtilityDTO;
import com.sue.cars.entity.ElectricUtility;

public interface electricUtilityService extends ServiceCRUD{
    ElectricUtilityDTO getElectric(String name);
}
