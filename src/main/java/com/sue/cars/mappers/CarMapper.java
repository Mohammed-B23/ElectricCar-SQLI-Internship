package com.sue.cars.mappers;

import com.sue.cars.dtos.*;
import com.sue.cars.dtos.diplay.DisplayCarDTO;
import com.sue.cars.entity.Car;
import com.sue.cars.entity.City;
import com.sue.cars.entity.ModelBrand;
import com.sue.cars.entity.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarMapper {


    private final ModelMapper modelMapper;
    private final CityMapper cityMapper;
    private final ElectricUtilityMapper electricUtilityMapper;
    private final ModelBrandMapper modelBrandMapper;

    public CarDTO carToDto(Car car){
          return modelMapper.map(car,CarDTO.class);
    }
    public Car CarDtoToCar(CarDTO carDTO){
        return modelMapper.map(carDTO,Car.class);
    }
    public DisplayCarDTO carToDisCarDto(Car car){
        DisplayCarDTO carDTO = modelMapper.map(car, DisplayCarDTO.class);
        //carDTO.setCityDTO(cityMapper.cityToCityDTO(car.getCity()));
        City city = car.getCity();
        State state  = city.getCountry().getState();
        ModelBrand modelBrand = car.getModelBrand();
        carDTO.setYear(modelBrand.getModelYear());
        carDTO.setCityName(city.getName());
        carDTO.setStateName(state.getName());
        carDTO.setModelName(modelBrand.getName());
        carDTO.setBrandName(modelBrand.getBrand().getName());
        carDTO.setCountryName(city.getCountry().getName());
        //carDTO.setElectricUtilityDTO(electricUtilityMapper.electricUtilityToElectricUtilityDTO(car.getElectricUtility()));
        //carDTO.setModelBrandDTO(modelBrandMapper.modelBrandToModelBrandDTO(car.getModelBrand()));
        return carDTO;
    }

}
