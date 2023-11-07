package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "vindolConst", columnNames = {"vin", "dolVehicleId"})
)
public class Car extends AbstractEntity{
    private String vin;
    private Long dolVehicleId;
    private Long baseMsrp;
    private Integer electricRange;
    private String vehicleLocation;
    @Enumerated(EnumType.STRING)
    private ElectricVehicleType electricType;
    @Enumerated(EnumType.STRING)
    private Eligibility eligibility;

    @ManyToOne
    private ElectricUtility electricUtility;
    @ManyToOne
    private ModelBrand modelBrand;
    @ManyToOne
    private City city;
}
