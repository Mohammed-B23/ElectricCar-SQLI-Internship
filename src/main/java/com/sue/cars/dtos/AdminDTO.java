package com.sue.cars.dtos;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private Long id;
    private String name;
    private String familyName;
    private Integer age;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private String email;
}
