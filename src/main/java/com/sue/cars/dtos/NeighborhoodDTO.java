package com.sue.cars.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodDTO {
    private Long id;
    private Integer postalCode;
}
