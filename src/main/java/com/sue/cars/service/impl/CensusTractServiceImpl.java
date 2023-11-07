package com.sue.cars.service.impl;

import com.sue.cars.dtos.CensusTractDTO;
import com.sue.cars.entity.CensusTract;
import com.sue.cars.entity.LegislativeDistinct;
import com.sue.cars.mappers.CensusTractMapper;
import com.sue.cars.repository.CensusTractRepository;
import com.sue.cars.repository.LegislativeDistinctRepository;
import com.sue.cars.service.CensusTractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CensusTractServiceImpl implements CensusTractService {
    @Autowired
    private CensusTractRepository censusTractRep;
    @Autowired
    private LegislativeDistinctRepository legislativeDistinctRepo;
    @Autowired
    private CensusTractMapper censusTractMapper;
    @Override
    public List<CensusTractDTO> getAll() {
        return censusTractRep.findAll().stream().map(
                censusTract -> censusTractMapper.censusTractToCensusTractDTO(censusTract)
        ).collect(Collectors.toList());
    }

    @Override
    public CensusTractDTO getById(Long id) {
        Optional<CensusTract> censusTract = censusTractRep.findById(id);
        if(censusTract.isPresent()){
            return censusTractMapper.censusTractToCensusTractDTO(censusTract.get());
        }
        return null;
    }

    @Override
    public CensusTractDTO getByCensusTract(Long census) {
        Optional<CensusTract> censusTract = censusTractRep.findCensusTractByCensusTract2020(census);
        if(censusTract.isPresent()){
            return censusTractMapper.censusTractToCensusTractDTO(censusTract.get());
        }
        return null;
    }

    @Override
    public CensusTractDTO addEntity(Object censusTractObject) {
         CensusTractDTO censusTractDTO = (CensusTractDTO) censusTractObject;
         CensusTract censusTract = censusTractMapper.censusTractDtoToCensusTract(censusTractDTO);
         Optional<LegislativeDistinct> legislativeDistinct = legislativeDistinctRepo.findById(censusTractDTO.getLegislativeId());
         if(legislativeDistinct.isPresent()){
             censusTract.setLegislativeDistinct(legislativeDistinct.get());
             return censusTractMapper.censusTractToCensusTractDTO(censusTractRep.save(censusTract));
         }
         return null;
    }

    @Override
    public Object updateEntity(Object censusTractDTO) {
        CensusTractDTO censusTractDTO1 = (CensusTractDTO) censusTractDTO;
        Optional<CensusTract> oldCensusTract = censusTractRep.findCensusTractByCensusTract2020(censusTractDTO1.getCensusTract2020());
        if(oldCensusTract.isPresent()){
            censusTractMapper.censusTractToCensusTractDTO(censusTractRep.save(censusTractMapper.censusTractDtoToCensusTract(censusTractDTO1)));
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        censusTractRep.deleteById(id);
    }
}
