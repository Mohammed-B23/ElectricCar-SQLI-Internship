package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.CensusTractDTO;
import com.sue.cars.service.CensusTractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/census")
public class censusTractController implements CRUDController{

    private final CensusTractService service;

    public censusTractController(CensusTractService service) {
        this.service = service;
    }

    @Override
    public List<CensusTractDTO> getAll() {
        return service.getAll();
    }

    @Override
    public CensusTractDTO getById(Long id) {
        return (CensusTractDTO) service.getById(id);
    }

    @Override
    public CensusTractDTO add(Object censusObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        CensusTractDTO censusDTO = objectMapper.convertValue(censusObject, CensusTractDTO.class);
        return (CensusTractDTO) service.addEntity(censusDTO);
    }

    @Override
    public CensusTractDTO update(Object censusObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        CensusTractDTO censusDTO = objectMapper.convertValue(censusObject, CensusTractDTO.class);
        return (CensusTractDTO) service.updateEntity(censusDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
