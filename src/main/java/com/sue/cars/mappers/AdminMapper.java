package com.sue.cars.mappers;

import com.sue.cars.dtos.AdminDTO;
import com.sue.cars.entity.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    @Autowired
    private ModelMapper modelMapper;
    public AdminDTO adminToAdminDTO(Admin admin){
        return modelMapper.map(admin, AdminDTO.class);
    }
    public Admin adminDtoToAdmin(AdminDTO adminDTO){
        return modelMapper.map(adminDTO, Admin.class);
    }
}
