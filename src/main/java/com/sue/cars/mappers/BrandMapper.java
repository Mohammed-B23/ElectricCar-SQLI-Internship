package com.sue.cars.mappers;

import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.entity.Brand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    @Autowired
    private ModelMapper modelMapper;
    public BrandDTO BrandToBrandDTO(Brand brand){
        return modelMapper.map(brand, BrandDTO.class);
    }

    public Brand BrandDtoToBrand(BrandDTO brandDTO){
        return modelMapper.map(brandDTO, Brand.class);
    }
}
