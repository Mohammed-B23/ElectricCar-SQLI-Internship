package com.sue.cars.mappers;

import com.sue.cars.dtos.LegislativeDistinctDTO;
import com.sue.cars.entity.LegislativeDistinct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LegislativeDistinctMapper {
    @Autowired
    private ModelMapper modelMapper;
    public LegislativeDistinctDTO legislativeToDTO(LegislativeDistinct legislativeDistinct){
        return modelMapper.map(legislativeDistinct, LegislativeDistinctDTO.class);
    }

    public LegislativeDistinct dtoToLegislative(LegislativeDistinctDTO legislativeDistinctDTO){
        return modelMapper.map(legislativeDistinctDTO, LegislativeDistinct.class);
    }
}
