package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.CityDTO;
import com.sue.cars.dtos.diplay.DisplayCityDTO;
import com.sue.cars.entity.City;
import com.sue.cars.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
public class cityController implements CRUDController2{
    @Autowired
    private CityService service;

    @Override
    public List<CityDTO> getAll() {
        return service.getAll();
    }

    @Override
    public DisplayCityDTO getById(Long id) {
        return (DisplayCityDTO) service.getById(id);
    }

    @Override
    public DisplayCityDTO getByName(String name) {
        return service.getCity(name);
    }

    @Override
    public DisplayCityDTO add(Object cityDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        CityDTO city = objectMapper.convertValue(cityDTO, CityDTO.class);
        return (DisplayCityDTO) service.addEntity(city);
    }

    @Override
    public DisplayCityDTO update(Object cityDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        CityDTO city = objectMapper.convertValue(cityDTO, CityDTO.class);
        return (DisplayCityDTO) service.updateEntity(city);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
