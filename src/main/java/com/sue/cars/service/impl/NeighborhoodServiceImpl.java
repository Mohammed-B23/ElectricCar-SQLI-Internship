package com.sue.cars.service.impl;

import com.sue.cars.dtos.NeighborhoodDTO;
import com.sue.cars.entity.Neighborhood;
import com.sue.cars.mappers.NeighborhoodMapper;
import com.sue.cars.repository.NeighborhoodRepository;
import com.sue.cars.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {
    @Autowired
    private NeighborhoodRepository neighborhoodRep;
    @Autowired
    private NeighborhoodMapper neighborhoodMapper;
    @Override
    public List<NeighborhoodDTO> getAll() {
        return neighborhoodRep.findAll().stream().map(
                neighborhood -> neighborhoodMapper.neighborhoodToNeighborhoodDTO(neighborhood)
        ).collect(Collectors.toList());
    }

    @Override
    public NeighborhoodDTO getById(Long id) {
        Optional<Neighborhood> neighborhood = neighborhoodRep.findById(id);
        if(neighborhood.isPresent()){
            return neighborhoodMapper.neighborhoodToNeighborhoodDTO(neighborhood.get());
        }
        return null;
    }

    @Override
    public NeighborhoodDTO getNeighborhoodByPostalCode(Integer postalCode) {
        Optional<Neighborhood> neighborhood = neighborhoodRep.findNeighborhoodByPostalCode(postalCode);
        if(neighborhood.isPresent()){
            return neighborhoodMapper.neighborhoodToNeighborhoodDTO(neighborhood.get());
        }
        return null;
    }

    @Override
    public NeighborhoodDTO addEntity(Object neighborhoodObject) {
        NeighborhoodDTO neighborhoodDTO = (NeighborhoodDTO) neighborhoodObject;
        return neighborhoodMapper
                .neighborhoodToNeighborhoodDTO(neighborhoodRep
                        .save(neighborhoodMapper.neighborhoodDtoToNeighborhood(neighborhoodDTO)));
    }

    @Override
    public NeighborhoodDTO updateEntity(Object neighborhoodObject) {
        NeighborhoodDTO neighborhoodDTO = (NeighborhoodDTO) neighborhoodObject;
        Optional<Neighborhood> oldNeighborhood = neighborhoodRep.findById(neighborhoodDTO.getId());
        if(oldNeighborhood.isPresent()){
             neighborhoodRep.save(neighborhoodMapper.neighborhoodDtoToNeighborhood(neighborhoodDTO));
             return neighborhoodDTO;
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        neighborhoodRep.deleteById(id);
    }
}
