package com.sue.cars.mappers;

import com.sue.cars.dtos.ElectricUtilityDTO;
import com.sue.cars.entity.ElectricUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElectricUtilityMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ElectricUtilityDTO electricUtilityToElectricUtilityDTO(ElectricUtility electricUtility){
        return modelMapper.map(electricUtility, ElectricUtilityDTO.class);
    }

    public ElectricUtility electricDtoToElectric(ElectricUtilityDTO electricUtilityDTO){
        return modelMapper.map(electricUtilityDTO, ElectricUtility.class);
    }
}
