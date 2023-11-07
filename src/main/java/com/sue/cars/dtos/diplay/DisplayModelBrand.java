package com.sue.cars.dtos.diplay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayModelBrand {
    private Long id;
    private String name;
    private Integer modelYear;
    private String brandName;
}
