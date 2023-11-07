package com.sue.cars.dtos;

import com.sue.cars.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelBrandDTO {
    private Long id;
    private String name;
    private Integer modelYear;
    private Long brandId;
}
