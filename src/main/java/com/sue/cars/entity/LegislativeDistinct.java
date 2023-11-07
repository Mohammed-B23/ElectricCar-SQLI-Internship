package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegislativeDistinct extends AbstractEntity {
    @Column(unique = true)
    private Integer legislativeDistinct;
    @OneToMany(mappedBy = "legislativeDistinct")
    private List<CensusTract> censusTracts;
}
