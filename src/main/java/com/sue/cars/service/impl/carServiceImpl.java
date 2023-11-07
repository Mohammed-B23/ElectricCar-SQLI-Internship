package com.sue.cars.service.impl;

import com.sue.cars.dtos.CarDTO;
import com.sue.cars.dtos.ModelBrandDTO;
import com.sue.cars.dtos.diplay.DisplayCarDTO;
import com.sue.cars.dtos.diplay.DisplayModelBrand;
import com.sue.cars.entity.*;
import com.sue.cars.mappers.CarMapper;
import com.sue.cars.mappers.ModelBrandMapper;
import com.sue.cars.repository.*;
import com.sue.cars.service.carService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class carServiceImpl implements carService {
    @Autowired
    private cityRepository cityRep;
    @Autowired
    private electricUtilityRepository electricUtilityRep;
    @Autowired
    private modelBrandRepository modelBrandRep;
    @Autowired
    private carRepository carRep;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ModelBrandMapper modelBrandMapper;
    @Autowired
    private countryRepository countryRep;
    @Autowired
    private stateRepository stateRep;

    @Override
    public List<DisplayCarDTO> getAllCars(int offset, int pageSize) {
        return carRep.findAll(PageRequest.of(offset, pageSize)).getContent()
                .stream().
                map(car -> carMapper.carToDisCarDto(car))
                .collect(Collectors.toList());
    }

    public Page<Car> getCarsByPage(int offset, int pageSize) {
        return carRep.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<DisplayCarDTO> getPageCarsByModelBran(List<DisplayCarDTO> cars ,int offset, int pageSize) {
        Pageable pageRequest = PageRequest.of(offset,pageSize);
        int start = (int) pageRequest.getOffset();
        int end = Math.min( (start + pageRequest.getPageSize()), cars.size() );
        List<DisplayCarDTO> pageContent = cars.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, cars.size());
    }

    @Override
    public List<String> getCarsByCity(Long idCity) {
        return carRep.findAllByCity(idCity);
    }

    @Override
    public List<String> getCarsByState(Long idState) {
        if(stateRep.findById(idState).isPresent()){
            State state = stateRep.findById(idState).get();
            return state.getCounties()
                    .stream()
                    .map(country -> country.getCities())
                    .flatMap(List::stream)
                    .map(city -> city.getCars())
                    .flatMap(List::stream)
                    .map(car -> car.getVehicleLocation())
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<DisplayCarDTO> getCarByModelBrand(ModelBrandDTO modelBrand) {
        return carRep.findCarByModelBrand(modelBrandMapper.modelBrandDTOToModelBrand(modelBrand))
                .stream()
                .map(car -> carMapper.carToDisCarDto(car))
                .collect(Collectors.toList());
    }


    @Override
    public List<DisplayCarDTO> getAll() {
        return carRep.findAll()
                .stream()
                .map(car -> carMapper.carToDisCarDto(car))
                .collect(Collectors.toList());
    }

    @Override
    public DisplayCarDTO getById(Long id) {
         Optional<Car> car = carRep.findById(id);
         if(car.isPresent()){
             System.out.println(car.get());
            return carMapper.carToDisCarDto(car.get());
         }else
             return null;
    }
    @Override
    public Page<Car> getCarsByVin(String vin, int offset, int pageSize) {
        return carRep.findAllByVin(vin,PageRequest.of(offset,pageSize));
    }


    @Override
    public DisplayCarDTO addEntity(Object carObject) {
        CarDTO carDTO = (CarDTO) carObject;
        Optional<City> city = cityRep.findById(carDTO.getCityId());
        Optional<ElectricUtility> electricUtility = electricUtilityRep.findById(carDTO.getElectricUtilityId());
        Optional<ModelBrand> modelBrand  = modelBrandRep.findById(carDTO.getModelBrandId());

        if(carDTO!=null){
            Car car = carMapper.CarDtoToCar(carDTO);
            car.setCity(city.get());
            car.setElectricUtility(electricUtility.get());
            car.setModelBrand(modelBrand.get());
            return carMapper.carToDisCarDto(carRep.save(car));
        }
        return null;
    }
    @Override
    public DisplayCarDTO updateEntity(Object carObject) {
        CarDTO carDTO = (CarDTO) carObject;
        Optional<City> city = cityRep.findById(carDTO.getCityId());
        Optional<ElectricUtility> electricUtility = electricUtilityRep.findById(carDTO.getElectricUtilityId());
        Optional<ModelBrand> modelBrand  = modelBrandRep.findById(carDTO.getModelBrandId());
        Optional<Car> oldCar = carRep.findById(carDTO.getId());
        if(oldCar.isPresent()){
            Car car = carMapper.CarDtoToCar(carDTO);
            car.setCity(city.get());
            car.setElectricUtility(electricUtility.get());
            car.setModelBrand(modelBrand.get());
            return carMapper.carToDisCarDto(carRep.save(car));
        }
        return null;
    }

    @Override
    public void deleteCars(String vin) {
        List<Car> cars = carRep.findByVin(vin);
        cars.stream().forEach(
                car -> {
                    carRep.delete(car);
                }
        );
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<Car> car = carRep.findById(id);
        if (car.isPresent())
            carRep.delete(car.get());
    }
}
