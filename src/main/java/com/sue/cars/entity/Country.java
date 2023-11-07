package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Country extends AbstractEntity{
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "country")
    private List<City> cities;
    @ManyToOne
    private State state;

}
