package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.StateDTO;
import com.sue.cars.dtos.diplay.DisplayStateDTO;
import com.sue.cars.entity.State;
import com.sue.cars.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/state")
public class stateController implements CRUDController2{
    @Autowired
    private StateService service;

    @Override
    public List getAll() {
        return service.getAll();
    }

    @Override
    public DisplayStateDTO getById(Long id) {
        return (DisplayStateDTO) service.getById(id);
    }

    @Override
    public DisplayStateDTO getByName(String name) {
        return service.getState(name);
    }

    @Override
    public DisplayStateDTO add(Object stateObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        StateDTO stateDTO = objectMapper.convertValue(stateObject, StateDTO.class);
        return (DisplayStateDTO) service.addEntity(stateDTO);
    }

    @Override
    public DisplayStateDTO update(Object stateObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        StateDTO stateDTO = objectMapper.convertValue(stateObject, StateDTO.class);
        return (DisplayStateDTO) service.updateEntity(stateDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
