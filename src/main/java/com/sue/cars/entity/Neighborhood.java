package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Neighborhood extends AbstractEntity{
    @Column(unique = true)
    private Integer postalCode;
    @ManyToMany(mappedBy = "neighborhoods")
    private List<City> cities;
    @ManyToMany
    @JoinTable(
            name = "neighborhood_census",
            joinColumns = @JoinColumn(name = "neighborhood_id"),
            inverseJoinColumns = @JoinColumn(name = "census_id"))
    private List<CensusTract> censusTracts;
}
