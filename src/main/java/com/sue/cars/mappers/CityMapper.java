package com.sue.cars.mappers;

import com.sue.cars.dtos.CityDTO;
import com.sue.cars.dtos.StateDTO;
import com.sue.cars.dtos.diplay.DisplayCityDTO;
import com.sue.cars.entity.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DisplayCityDTO cityToCityDTO(City city){
        DisplayCityDTO displayCityDTO = modelMapper.map(city, DisplayCityDTO.class);
        displayCityDTO.setStateName(city.getCountry().getState().getName());
        displayCityDTO.setCountryName(city.getCountry().getName());
         return displayCityDTO;
    }
    public City cityDispDtoToCity(DisplayCityDTO displayCityDTO){
        City city = modelMapper.map(displayCityDTO,City.class);
        return city;
    }

    public City cityDtoToCity(CityDTO cityDTO){
        City city = modelMapper.map(cityDTO,City.class);
        return city;
    }

}
