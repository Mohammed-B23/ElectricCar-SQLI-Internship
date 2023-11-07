package com.sue.cars.dtos.diplay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayCountryDTO {
    private Long id;
    private String name;
    private String stateName;
}
