package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.NeighborhoodDTO;
import com.sue.cars.service.NeighborhoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/neighborhood")
public class neighborhoodController implements CRUDController{

    private final NeighborhoodService service;

    public neighborhoodController(NeighborhoodService service) {
        this.service = service;
    }

    @Override
    public List<NeighborhoodDTO> getAll() {
        return service.getAll();
    }

    @Override
    public NeighborhoodDTO getById(Long id) {
        return (NeighborhoodDTO) service.getById(id);
    }

    @GetMapping
    public NeighborhoodDTO getByPostalCode(@RequestParam(value = "postalCode") Integer postalCode) {
        return service.getNeighborhoodByPostalCode(postalCode);
    }

    @Override
    public NeighborhoodDTO add(Object neighborhoodObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        NeighborhoodDTO neighborhoodDTO = objectMapper.convertValue(neighborhoodObject, NeighborhoodDTO.class);
        return (NeighborhoodDTO) service.addEntity(neighborhoodDTO);
    }

    @Override
    public NeighborhoodDTO update(Object neighborhoodObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        NeighborhoodDTO neighborhoodDTO = objectMapper.convertValue(neighborhoodObject, NeighborhoodDTO.class);
        return (NeighborhoodDTO) service.updateEntity(neighborhoodDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
