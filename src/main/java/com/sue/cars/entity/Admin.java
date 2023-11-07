package com.sue.cars.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends AbstractEntity{
    private String name;
    private String familyName;
    private Integer age;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private String email;

    public Admin(String name, String familyName, Integer age, String email) {
        this.name = name;
        this.familyName = familyName;
        this.age = age;
        this.email = email;
    }

}
