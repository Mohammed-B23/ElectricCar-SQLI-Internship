package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.entity.Brand;
import com.sue.cars.service.brandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class brandController implements CRUDController2{
    @Autowired
    private brandService service;
    @Override
    public List<BrandDTO> getAll() {
        return service.getAll();
    }

    @Override
    public BrandDTO getById(Long id) {
        return (BrandDTO) service.getById(id);
    }

    @Override
    public BrandDTO getByName(String name) {
        return service.getBrand(name);
    }

    @Override
    public BrandDTO add(Object brandObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        BrandDTO brandDTO = objectMapper.convertValue(brandObject, BrandDTO.class);
        return (BrandDTO) service.addEntity(brandDTO);
    }

    @Override
    public BrandDTO update(Object brandObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        BrandDTO brandDTO = objectMapper.convertValue(brandObject, BrandDTO.class);
        return (BrandDTO) service.updateEntity(brandDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
