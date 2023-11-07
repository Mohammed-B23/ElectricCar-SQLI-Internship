package com.sue.cars.service;

import com.sue.cars.dtos.CountryDTO;
import com.sue.cars.dtos.diplay.DisplayCountryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService extends ServiceCRUD{
    public DisplayCountryDTO getCountry(String name);
    Page<DisplayCountryDTO> getCountriesByPage(int offset, int pageSize);
}
