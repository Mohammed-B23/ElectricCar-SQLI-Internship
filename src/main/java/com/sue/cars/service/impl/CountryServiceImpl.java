package com.sue.cars.service.impl;

import com.sue.cars.dtos.CityDTO;
import com.sue.cars.dtos.CountryDTO;
import com.sue.cars.dtos.diplay.DisplayCountryDTO;
import com.sue.cars.entity.City;
import com.sue.cars.entity.Country;
import com.sue.cars.entity.State;
import com.sue.cars.mappers.CityMapper;
import com.sue.cars.mappers.CountryMapper;
import com.sue.cars.repository.countryRepository;
import com.sue.cars.repository.stateRepository;
import com.sue.cars.service.CountryService;
import com.sue.cars.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private countryRepository countryRep;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private stateRepository stateRep;

    @Override
    public List<DisplayCountryDTO> getAll() {
        return countryRep.findAll().stream().map(country -> countryMapper.countryToDisplayCountryDTO(country)).collect(Collectors.toList());
    }

    @Override
    public DisplayCountryDTO getCountry(String name) {
        Optional<Country> country = countryRep.findByName(name);
        if(country.isPresent())
            return countryMapper.countryToDisplayCountryDTO(country.get());
        return null;
    }

    @Override
    public Page<DisplayCountryDTO> getCountriesByPage(int offset, int pageSize) {
         List<DisplayCountryDTO> countryDTOS = countryRep.findAll(PageRequest.of(offset, pageSize))
                .stream()
                .map(country -> countryMapper.countryToDisplayCountryDTO(country))
                .collect(Collectors.toList());
         return new PageImpl<>(countryDTOS);
    }

    @Override
    public DisplayCountryDTO getById(Long id) {
        Optional<Country> country = countryRep.findById(id);
        if(country.isPresent())
            return countryMapper.countryToDisplayCountryDTO(country.get());
        return null;
    }

    @Override
    public CountryDTO addEntity(Object countryObject) {
        CountryDTO countryDTO = (CountryDTO) countryObject;
        Optional<State> state = stateRep.findById(countryDTO.getIdState());
        if(state.isPresent()){
            Country country = countryMapper.countryDtoToCountry(countryDTO);
            country.setState(state.get());
            return countryMapper.countryToCountryDTO(countryRep.save(country));
        }
        return null;
    }

    @Override
    public CountryDTO updateEntity(Object countryObject) {
        CountryDTO countryDTO = (CountryDTO) countryObject;
        if(countryRep.findById(countryDTO.getId()).isPresent()){
            Optional<State> state = stateRep.findById(countryDTO.getIdState());
                Country country = countryMapper.countryDtoToCountry(countryDTO);
                country.setState(state.get());
                return countryMapper.countryToCountryDTO(countryRep.save(country));
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<Country> country = countryRep.findById(id);
        if(country.isPresent())
            countryRep.delete(country.get());
        else
            System.out.println("This Country Is Not Exist!");
    }
}
