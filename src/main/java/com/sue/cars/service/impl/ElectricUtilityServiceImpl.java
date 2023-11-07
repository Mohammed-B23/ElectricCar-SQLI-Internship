package com.sue.cars.service.impl;

import com.sue.cars.dtos.ElectricUtilityDTO;
import com.sue.cars.entity.ElectricUtility;
import com.sue.cars.mappers.ElectricUtilityMapper;
import com.sue.cars.repository.electricUtilityRepository;
import com.sue.cars.service.electricUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectricUtilityServiceImpl implements electricUtilityService {

    @Autowired
    private electricUtilityRepository utilityRepository;
    @Autowired
    private ElectricUtilityMapper electricUtilityMapper;


    @Override
    public List<ElectricUtilityDTO> getAll() {
        return utilityRepository.findAll().stream()
                .map(electricUtility -> electricUtilityMapper.electricUtilityToElectricUtilityDTO(electricUtility))
                .collect(Collectors.toList());
    }

    @Override
    public ElectricUtilityDTO getById(Long id) {
        Optional<ElectricUtility> electricUtility = utilityRepository.findById(id);
        if(electricUtility.isPresent())
            return electricUtilityMapper.electricUtilityToElectricUtilityDTO(electricUtility.get());
        return null;
    }

    @Override
    public ElectricUtilityDTO getElectric(String name) {
        Optional<ElectricUtility> electricUtility = utilityRepository.findByName(name);
        if(electricUtility.isPresent())
            return electricUtilityMapper.electricUtilityToElectricUtilityDTO(electricUtility.get());
        return null;
    }

    @Override
    public ElectricUtilityDTO addEntity(Object electricUtilityObject) {
        ElectricUtilityDTO electricUtilityDTO = (ElectricUtilityDTO) electricUtilityObject;
        utilityRepository.save(electricUtilityMapper.electricDtoToElectric(electricUtilityDTO));
        return electricUtilityDTO;
    }

    @Override
    public ElectricUtility updateEntity(Object electricUtilityObject) {
        ElectricUtilityDTO electricUtilityDTO = (ElectricUtilityDTO) electricUtilityObject;
        if(utilityRepository.findById(electricUtilityDTO.getId()).isPresent())
            return utilityRepository.save(electricUtilityMapper.electricDtoToElectric(electricUtilityDTO));
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<ElectricUtility> electricUtility = utilityRepository.findById(id);
        if(electricUtility.isPresent())
            utilityRepository.delete(electricUtility.get());
    }
}
