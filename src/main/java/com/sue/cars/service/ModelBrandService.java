package com.sue.cars.service;

import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.dtos.ModelBrandDTO;
import com.sue.cars.dtos.diplay.DisplayModelBrand;
import com.sue.cars.entity.Brand;
import com.sue.cars.entity.ModelBrand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ModelBrandService extends ServiceCRUD{
    DisplayModelBrand getModelBrand(String name);
    List<ModelBrand> getModelBrandBySoundex(String name);
    List getAllByPage(int offset, int pageSize);
    List<DisplayModelBrand> getByModelYearAndBrand(BrandDTO brandDTO, int modelYear);
    List<Integer> gerDistinctModelYear();
    Map<String,Long> getBrandsByModelYear(Integer modelYear);
    Page<ModelBrand> getModelBrandPage(int currentPage, int pageSize);
    List<ModelBrand> getModelBrandByBrand(Brand brand);
    Map<Integer,Long> getModelYearsByBrand(String brandName);

}
