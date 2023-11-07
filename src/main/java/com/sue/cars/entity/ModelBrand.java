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

@Entity
@Getter
@Setter
public class ModelBrand extends AbstractEntity{
    @Column(unique = true)
    private String name;
    private Integer modelYear;
    @ManyToOne
    private Brand brand;
    @OneToMany(mappedBy = "modelBrand")
    private List<Car> cars;
}
