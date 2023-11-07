package com.sue.cars.service.impl;

import com.sqli.phonetic.services.GenericServ;
import com.sqli.phonetic.services.GenericService;
import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.dtos.ModelBrandDTO;
import com.sue.cars.dtos.diplay.DisplayModelBrand;
import com.sue.cars.entity.Brand;
import com.sue.cars.entity.ModelBrand;
import com.sue.cars.mappers.BrandMapper;
import com.sue.cars.mappers.ModelBrandMapper;
import com.sue.cars.repository.brandRepository;
import com.sue.cars.repository.modelBrandRepository;
import com.sue.cars.service.ModelBrandService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ModelBrandServiceImp implements ModelBrandService {

    @Autowired
    private modelBrandRepository modelBrandRep;
    @Autowired
    private brandRepository brandRep;
    @Autowired
    private ModelBrandMapper modelBrandMapper;
    @Autowired
    private BrandMapper brandMapper;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DisplayModelBrand> getAll() {
        return modelBrandRep.findAll().stream().
                map(modelBrand -> modelBrandMapper.modelBrandToModelBrandDTO(modelBrand))
                .collect(Collectors.toList());
    }

    @Override
    public DisplayModelBrand getModelBrand(String name) {
        Optional<ModelBrand> modelBrand = modelBrandRep.findByName(name);
        if(modelBrand.isPresent()){
            return modelBrandMapper.modelBrandToModelBrandDTO(modelBrand.get());
        }
        return null;
    }

    @Override
    public List<ModelBrand> getModelBrandBySoundex(String param) {
        Map<String, String> columnsModelBrand = new HashMap<>();
        columnsModelBrand.put("name",param);
        GenericServ<ModelBrand> genericServ = new GenericServ<>(em);
        return genericServ.soundex(ModelBrand.class, columnsModelBrand);
    }

    @Override
    public List getAllByPage(int offset, int pageSize) {
        return modelBrandRep.findAll(PageRequest.of(offset, pageSize)).stream().
                map(modelBrand -> modelBrandMapper.modelBrandToModelBrandDTO(modelBrand))
                .collect(Collectors.toList());
    }

    @Override
    public List<DisplayModelBrand> getByModelYearAndBrand(BrandDTO brandDTO, int modelYear) {
        return modelBrandRep.findModelBrandByBrandAndModelYear(
            brandMapper.BrandDtoToBrand(brandDTO),
                modelYear
        ).stream().map(modelBrand -> modelBrandMapper.modelBrandToModelBrandDTO(modelBrand)).collect(Collectors.toList());
    }

    @Override
    public List<Integer> gerDistinctModelYear() {
        return modelBrandRep.getDistinctModelYear();
    }

    @Override
    public Map<String,Long> getBrandsByModelYear(Integer modelYear) {
        Map<String,Long> map = modelBrandRep.getDistinctByModelYear(modelYear).stream().collect(Collectors.toMap(
                row-> (String) row[0],
                row -> (Long) row[1]
        ));

        return map;
    }

    @Override
    public Map<Integer,Long> getModelYearsByBrand(String brandName) {
        Map<Integer,Long> map = modelBrandRep.getDistinctByBrand(brandName).stream().collect(Collectors.toMap(
                row-> (Integer) row[0],
                row -> (Long) row[1]
        ));
        return map;
    }


    @Override
    public Page<ModelBrand> getModelBrandPage(int currentPage, int pageSize) {
        return modelBrandRep.findAll(PageRequest.of(currentPage, pageSize));
    }

    @Override
    public List<ModelBrand> getModelBrandByBrand(Brand brand) {
        return modelBrandRep.findModelBrandByBrand(brand);
    }

    @Override
    public DisplayModelBrand getById(Long id) {
        Optional<ModelBrand> modelBrand = modelBrandRep.findById(id);
        if(modelBrand.isPresent()){
            return modelBrandMapper.modelBrandToModelBrandDTO(modelBrand.get());
        }
        return null;
    }

    @Override
    public DisplayModelBrand addEntity(Object modelBrandObject) {
        ModelBrandDTO modelBrandDTO = (ModelBrandDTO) modelBrandObject;
        Optional<Brand> brand  = brandRep.findById(modelBrandDTO.getBrandId());
        if(brand.isPresent()){
            ModelBrand modelBrand = modelBrandMapper.modelBrandDTOToModelBrand(modelBrandDTO);
            modelBrand.setBrand(brand.get());
            return modelBrandMapper.modelBrandToModelBrandDTO(modelBrandRep.save(modelBrand));
        }
        return null;
    }

    @Override
    public DisplayModelBrand updateEntity(Object modelBrandObject) {
        ModelBrandDTO modelBrandDTO = (ModelBrandDTO) modelBrandObject;
        if(modelBrandRep.findById(modelBrandDTO.getId()).isPresent()){
             ModelBrand modelBrand = modelBrandMapper.modelBrandDTOToModelBrand(modelBrandDTO);
             modelBrand.setBrand(brandRep.findById(modelBrandDTO.getBrandId()).get());
            return modelBrandMapper.modelBrandToModelBrandDTO(modelBrandRep.save(modelBrand));
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<ModelBrand> modelBrand = modelBrandRep.findById(id);
        if(modelBrand.isPresent()){
            modelBrandRep.delete(modelBrand.get());
        }
    }
}
