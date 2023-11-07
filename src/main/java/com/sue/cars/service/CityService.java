package com.sue.cars.service;

import com.sue.cars.dtos.CityDTO;
import com.sue.cars.dtos.diplay.DisplayCityDTO;
import com.sue.cars.entity.City;
import org.springframework.data.domain.Page;

public interface CityService extends ServiceCRUD{
    DisplayCityDTO getCity(String name);
    Page<City> getAllByPage(int offset, int pageSize);
}
