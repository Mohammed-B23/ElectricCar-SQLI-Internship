package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.ElectricUtilityDTO;
import com.sue.cars.service.electricUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/electricutility")
public class electricUtilityController implements CRUDController2{
    @Autowired
    private electricUtilityService service;

    @Override
    public List getAll() {
        return service.getAll();
    }

    @Override
    public ElectricUtilityDTO getById(Long id) {
        return (ElectricUtilityDTO) service.getById(id);
    }

    @Override
    public ElectricUtilityDTO getByName(String name) {
        return service.getElectric(name);
    }

    @Override
    public ElectricUtilityDTO add(Object electricObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        ElectricUtilityDTO electricUtilityDTO = objectMapper.convertValue(electricObject, ElectricUtilityDTO.class);
        return (ElectricUtilityDTO) service.addEntity(electricUtilityDTO);
    }

    @Override
    public ElectricUtilityDTO update(Object electricObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        ElectricUtilityDTO electricDTO = objectMapper.convertValue(electricObject, ElectricUtilityDTO.class);
        return (ElectricUtilityDTO) service.updateEntity(electricDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
