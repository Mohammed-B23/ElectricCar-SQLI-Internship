package com.sue.cars.dtos;

import com.sue.cars.entity.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String vin;
    private Long dolVehicleId;
    private Long baseMsrp;
    private Integer electricRange;
    private String vehicleLocation;
    @Enumerated(EnumType.STRING)
    private ElectricVehicleType electricType;
    @Enumerated(EnumType.STRING)
    private Eligibility eligibility;
    private Long cityId;
    private Long modelBrandId;
    private Long electricUtilityId;
}
