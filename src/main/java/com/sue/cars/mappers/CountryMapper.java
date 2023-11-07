package com.sue.cars.mappers;

import com.sue.cars.dtos.CountryDTO;
import com.sue.cars.dtos.diplay.DisplayCountryDTO;
import com.sue.cars.entity.Country;
import com.sue.cars.entity.State;
import com.sue.cars.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CountryDTO countryToCountryDTO(Country country){
        CountryDTO countryDTO = modelMapper.map(country, CountryDTO.class);
        countryDTO.setIdState(country.getState().getId());
        return countryDTO;
    }
    public Country countryDtoToCountry(CountryDTO countryDTO){
        Country country = modelMapper.map(countryDTO, Country.class);
        return country;
    }

    public DisplayCountryDTO countryToDisplayCountryDTO(Country country){
        DisplayCountryDTO displayCountryDTO = modelMapper.map(country, DisplayCountryDTO.class);
        displayCountryDTO.setStateName(country.getState().getName());
        return displayCountryDTO;
    }


}
