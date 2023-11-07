package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CensusTract extends AbstractEntity {
    @Column(unique = true)
    private Long censusTract2020;
    @ManyToMany(mappedBy = "censusTracts")
    private List<Neighborhood> neighborhoods;
    @ManyToOne
    private LegislativeDistinct legislativeDistinct;
}
