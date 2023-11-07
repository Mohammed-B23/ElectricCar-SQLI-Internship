package com.sue.cars.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.LegislativeDistinctDTO;
import com.sue.cars.service.LegislativeDistinctService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/legislative")
public class legislativeDistinctController implements CRUDController{
    private final LegislativeDistinctService service;

    public legislativeDistinctController(LegislativeDistinctService service) {
        this.service = service;
    }

    @Override
    public List<LegislativeDistinctDTO> getAll() {
        return service.getAll();
    }

    @Override
    public LegislativeDistinctDTO getById(Long id) {
        return (LegislativeDistinctDTO) service.getById(id);
    }

    @GetMapping("/legislative/{legislative}")
    public LegislativeDistinctDTO getByLegislative(@PathVariable Integer legislative){
        return service.getByLegislative(legislative);
    }

    @Override
    public LegislativeDistinctDTO add(Object legislativeObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        LegislativeDistinctDTO legislativeDTO = objectMapper.convertValue(legislativeObject, LegislativeDistinctDTO.class);
        return (LegislativeDistinctDTO) service.addEntity(legislativeDTO);
    }

    @Override
    public LegislativeDistinctDTO update(Object legislativeObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        LegislativeDistinctDTO legislativeDTO = objectMapper.convertValue(legislativeObject, LegislativeDistinctDTO.class);
        return (LegislativeDistinctDTO) service.updateEntity(legislativeDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}

