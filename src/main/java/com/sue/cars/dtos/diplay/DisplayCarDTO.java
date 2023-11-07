package com.sue.cars.dtos.diplay;

import com.sue.cars.entity.ElectricVehicleType;
import com.sue.cars.entity.Eligibility;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayCarDTO {
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
    private String cityName;
    private String stateName;
    private String countryName;
    private String modelName;
    private String electricName;
    private String brandName;
    private Integer year;
}
