package com.sue.cars.controller;

import com.sue.cars.dtos.AdminDTO;
import com.sue.cars.entity.Admin;
import com.sue.cars.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class adminController {
    // @autowired;
    private AdminService adminService;
    adminController(AdminService adminService){
        this.adminService = adminService;
    }
    @PostMapping("/addAdmin")
    public AdminDTO addAdmin(@RequestBody AdminDTO adminDTO){
        return (AdminDTO) adminService.addEntity(adminDTO);
    }

    @GetMapping("/all")
    public List<AdminDTO> getAllAdmin(){
        return adminService.getAll();
    }

    @GetMapping("/{id}")
    public AdminDTO getAdminById(@PathVariable Long id){
        return (AdminDTO) adminService.getById(id);
    }

    @PutMapping("/update")
    public AdminDTO updateAdmin(@RequestBody AdminDTO adminDTO){
        return (AdminDTO) adminService.updateEntity(adminDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteEntity(id);
    }



}
