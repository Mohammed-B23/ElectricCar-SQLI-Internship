package com.sue.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Entity
@Getter
@Setter
public class ElectricUtility extends AbstractEntity{
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "electricUtility")
    private List<Car> cars;
}
