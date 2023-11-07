package com.sue.cars.dtos;

import com.sue.cars.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO implements Serializable {
    private Long id;
    private String name;
    private Long countryId;
}
