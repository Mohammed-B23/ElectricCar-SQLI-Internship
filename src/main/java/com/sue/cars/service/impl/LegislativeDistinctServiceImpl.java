package com.sue.cars.service.impl;

import com.sue.cars.dtos.LegislativeDistinctDTO;
import com.sue.cars.entity.LegislativeDistinct;
import com.sue.cars.mappers.LegislativeDistinctMapper;
import com.sue.cars.repository.LegislativeDistinctRepository;
import com.sue.cars.service.LegislativeDistinctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LegislativeDistinctServiceImpl implements LegislativeDistinctService {
    @Autowired
    private LegislativeDistinctRepository legislativeDistinctRep;
    @Autowired
    private LegislativeDistinctMapper legislativeDistinctMapper;

    @Override
    public List<LegislativeDistinctDTO> getAll() {
        return legislativeDistinctRep.findAll().stream().
                map(legislativeDistinct -> legislativeDistinctMapper.legislativeToDTO(legislativeDistinct))
                .collect(Collectors.toList());
    }

    @Override
    public LegislativeDistinctDTO getById(Long id) {
        Optional<LegislativeDistinct> legislativeDistinct = legislativeDistinctRep.findById(id);
        if(legislativeDistinct.isPresent()){
            return legislativeDistinctMapper.legislativeToDTO(legislativeDistinct.get());
        }
        return null;
    }

    @Override
    public LegislativeDistinctDTO getByLegislative(Integer legislative){
        Optional<LegislativeDistinct> legislativeDistinct = legislativeDistinctRep.findLegislativeDistinctByLegislativeDistinct(legislative);
        if(legislativeDistinct.isPresent()){
            return legislativeDistinctMapper.legislativeToDTO(legislativeDistinct.get());
        }
        return null;
    }

    @Override
    public LegislativeDistinctDTO addEntity(Object legislativeDistinctDTO) {
        return legislativeDistinctMapper
                .legislativeToDTO(legislativeDistinctRep
                        .save(legislativeDistinctMapper.
                                dtoToLegislative((LegislativeDistinctDTO) legislativeDistinctDTO)
        ));
    }

    @Override
    public LegislativeDistinctDTO updateEntity(Object legislativeDistinctDTO) {
        LegislativeDistinctDTO legislativeDistinctDTO1 = (LegislativeDistinctDTO) legislativeDistinctDTO;
        Optional<LegislativeDistinct> legislativeDistinct = legislativeDistinctRep.findById(legislativeDistinctDTO1.getId());
        if(legislativeDistinct.isPresent()){
            legislativeDistinctRep.save(legislativeDistinct.get());
            return legislativeDistinctDTO1;
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        legislativeDistinctRep.deleteById(id);
    }
}
