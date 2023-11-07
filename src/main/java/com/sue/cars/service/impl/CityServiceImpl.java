package com.sue.cars.service.impl;

import com.sue.cars.dtos.CityDTO;
import com.sue.cars.dtos.diplay.DisplayCityDTO;
import com.sue.cars.entity.Car;
import com.sue.cars.entity.City;
import com.sue.cars.entity.Country;
import com.sue.cars.entity.State;
import com.sue.cars.mappers.CityMapper;
import com.sue.cars.repository.cityRepository;
import com.sue.cars.repository.countryRepository;
import com.sue.cars.repository.stateRepository;
import com.sue.cars.service.CityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private cityRepository cityRep;
    @Autowired
    private stateRepository stateRep;
    @Autowired
    private countryRepository countryRep;
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<DisplayCityDTO> getAll() {
        return cityRep.findAll().stream().map(city -> cityMapper.cityToCityDTO(city)).collect(Collectors.toList());
    }

    @Override
    public Page<City> getAllByPage(int offset, int pageSize) {
        return cityRep.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public DisplayCityDTO getCity(String name) {
        Optional<City> city = cityRep.findByName(name);
        if(city.isPresent())
            return cityMapper.cityToCityDTO(city.get());
        return null;
    }

    @Override
    public DisplayCityDTO getById(Long id) {
        Optional<City> city = cityRep.findById(id);
        if(city.isPresent())
            return cityMapper.cityToCityDTO(city.get());
        return null;
    }

    @Override
    public DisplayCityDTO addEntity(Object cityobject) {
        CityDTO cityDTO = (CityDTO) cityobject;
        Optional<Country> country = countryRep.findById(cityDTO.getCountryId());
        if(country.isPresent()){
            City city = cityMapper.cityDtoToCity(cityDTO);
            city.setCountry(country.get());
            return cityMapper.cityToCityDTO(cityRep.save(city));
        }
        return null;
    }

    @Override
    public DisplayCityDTO updateEntity(Object cityObject) {
        CityDTO cityDTO = (CityDTO) cityObject;
        if (cityRep.findById(cityDTO.getId()).isPresent()){
            City city = cityMapper.cityDtoToCity(cityDTO);
            city.setCountry(countryRep.findById(cityDTO.getCountryId()).get());
            return cityMapper.cityToCityDTO(cityRep.save(city));
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<City> city = cityRep.findById(id);
        if (city.isPresent())
            cityRep.delete(city.get());
        else
            System.out.println("this city not exist!");
    }
}
