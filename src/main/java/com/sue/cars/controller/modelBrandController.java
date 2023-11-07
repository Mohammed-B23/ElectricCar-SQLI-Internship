package com.sue.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sue.cars.dtos.ModelBrandDTO;
import com.sue.cars.dtos.diplay.DisplayModelBrand;
import com.sue.cars.entity.ModelBrand;
import com.sue.cars.service.ModelBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/modelbrand")
public class modelBrandController implements CRUDController2{
    @Autowired
    private ModelBrandService service;
    @Override
    public List<DisplayModelBrand> getAll() {
        return service.getAll();
    }

    @Override
    public DisplayModelBrand getById(Long id) {
        return (DisplayModelBrand) service.getById(id);
    }

    @Override
    public DisplayModelBrand getByName(String name) {
        return service.getModelBrand(name);
    }

    @Override
    public DisplayModelBrand add(Object modelBrandObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        ModelBrandDTO modelBrandDTO = objectMapper.convertValue(modelBrandObject, ModelBrandDTO.class);
        return (DisplayModelBrand)service.addEntity(modelBrandDTO);
    }

    @Override
    public DisplayModelBrand update(Object modelBrandObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        ModelBrandDTO modelBrandDTO = objectMapper.convertValue(modelBrandObject, ModelBrandDTO.class);
        return (DisplayModelBrand) service.updateEntity(modelBrandDTO);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteEntity(id);
    }
}
