package com.sue.cars.mappers;

import com.sue.cars.dtos.CensusTractDTO;
import com.sue.cars.entity.CensusTract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CensusTractMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LegislativeDistinctMapper legislativeDistinctMapper;
    public CensusTractDTO censusTractToCensusTractDTO(CensusTract censusTract){
        CensusTractDTO censusTractDTO = modelMapper.map(censusTract, CensusTractDTO.class);
        censusTractDTO.setLegislativeId(censusTract.getLegislativeDistinct().getId());
        //censusTractDTO.setLegislativeDistinctDTO(legislativeDistinctMapper.legislativeToDTO(censusTract.getLegislativeDistinct()));
        return censusTractDTO;
    }
    public CensusTract censusTractDtoToCensusTract(CensusTractDTO censusTractDTO){
        CensusTract censusTract = modelMapper.map(censusTractDTO, CensusTract.class);
        //censusTract.setLegislativeDistinct(legislativeDistinctMapper.dtoToLegislative(censusTractDTO.getLegislativeDistinctDTO()));
        return censusTract;
    }
}
