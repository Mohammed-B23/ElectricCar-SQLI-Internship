package com.sue.cars.service;

import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface brandService extends ServiceCRUD{
     BrandDTO getBrand(String name);
     List<Brand> getBrandBySoundex(String param);
     Page<BrandDTO> getBrandByPage(int offset, int pageSize);
}
