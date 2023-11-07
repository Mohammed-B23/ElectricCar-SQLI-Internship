package com.sue.cars.dtos.diplay;

import com.sue.cars.dtos.StateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisplayCityDTO {
    private Long id;
    private String name;
    private String stateName;
    private String countryName;
}
