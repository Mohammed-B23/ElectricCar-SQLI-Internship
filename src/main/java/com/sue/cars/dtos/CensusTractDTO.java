package com.sue.cars.dtos;

import com.sue.cars.entity.LegislativeDistinct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CensusTractDTO {
    private Long id;
    private Long censusTract2020;
    //private LegislativeDistinctDTO legislativeDistinctDTO;
    private Long legislativeId;
}
