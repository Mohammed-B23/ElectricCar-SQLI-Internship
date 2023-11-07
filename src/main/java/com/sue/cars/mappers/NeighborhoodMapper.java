package com.sue.cars.mappers;

import com.sue.cars.dtos.NeighborhoodDTO;
import com.sue.cars.entity.Neighborhood;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NeighborhoodMapper {
    @Autowired
    private ModelMapper modelMapper;
    public NeighborhoodDTO neighborhoodToNeighborhoodDTO(Neighborhood neighborhood){
        return modelMapper.map(neighborhood, NeighborhoodDTO.class);
    }
    public Neighborhood neighborhoodDtoToNeighborhood(NeighborhoodDTO neighborhoodDTO){
        return modelMapper.map(neighborhoodDTO, Neighborhood.class);
    }
}
