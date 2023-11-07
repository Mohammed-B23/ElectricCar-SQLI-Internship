package com.sue.cars.service.impl;

import com.sue.cars.dtos.AdminDTO;
import com.sue.cars.entity.Admin;
import com.sue.cars.mappers.AdminMapper;
import com.sue.cars.repository.adminRepository;
import com.sue.cars.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private adminRepository adminRep;
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<AdminDTO> getAll() {
        return adminRep.findAll().stream().map(admin -> adminMapper.adminToAdminDTO(admin)).collect(Collectors.toList());
    }

    @Override
    public AdminDTO getById(Long id) {
        return adminRep.findById(id).isPresent() ? adminMapper.adminToAdminDTO(adminRep.findById(id).get()) : null;
    }

    @Override
    public AdminDTO addEntity(Object adminObject) {
        AdminDTO adminDTO = (AdminDTO) adminObject;
        adminRep.save(adminMapper.adminDtoToAdmin(adminDTO));
        return adminDTO;
    }

    @Override
    public AdminDTO updateEntity(Object adminObject) {
        AdminDTO adminDTO = (AdminDTO)adminObject;
        Optional<Admin> oldAdmin = adminRep.findById(adminDTO.getId());
        if(oldAdmin.isPresent()){
            adminRep.save(adminMapper.adminDtoToAdmin(adminDTO));
            return adminDTO;
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<Admin> admin = adminRep.findById(id);
        if(admin.isPresent()){
            adminRep.deleteById(id);
        }else
            System.out.println("this admin not exist!");
    }
}
