package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class City extends AbstractEntity {
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Car> cars;
    @ManyToOne
    private Country country;
    @ManyToMany
    @JoinTable(
            name = "city_neighborhood",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "neighborhood_id"))
    private List<Neighborhood> neighborhoods;

}
