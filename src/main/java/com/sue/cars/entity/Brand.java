package com.sue.cars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Brand extends AbstractEntity{
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "brand")
    private List<ModelBrand> modelBrands;
}
