package com.sue.cars.controller.thymeleaf;

import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.entity.Brand;
import com.sue.cars.mappers.BrandMapper;
import com.sue.cars.mappers.CarMapper;
import com.sue.cars.mappers.ModelBrandMapper;
import com.sue.cars.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ViewBrand {
    private final carService carServ;
    private final CountryService countryServ;
    private final ModelBrandService modelBrandService;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ModelBrandMapper modelBrandMapper;
    @Autowired
    private brandService brandServ;
    @Autowired
    private PaginationService paginationService;
    private final RestTemplate restTemplate;

    public ViewBrand(carService carServ, RestTemplate restTemplate, CountryService countryServ, ModelBrandService modelBrandService) {
        this.carServ = carServ;
        this.restTemplate = restTemplate;
        this.countryServ = countryServ;
        this.modelBrandService = modelBrandService;
    }

    @GetMapping("/searchBrands")
    public String searchBrands(Model model, @RequestParam(name = "name") String name) {
        List<BrandDTO> brands = brandServ.getBrandBySoundex(name).stream().map(brand -> brandMapper.BrandToBrandDTO(brand)).collect(Collectors.toList());
        model.addAttribute("brands", brands);
        return "searchBrand";
    }

    @GetMapping("/viewBrands")
    public String viewBrands(Model model, @RequestParam Optional<Integer> offset, @RequestParam Optional<Integer> pageSize, @RequestParam Optional<Integer> totalPage) {
        int currentPage = offset.orElse(1);
        int size = pageSize.orElse(20);
        if(offset.orElse(1)<=0){
            currentPage = 1;
        }
        if (totalPage.isPresent())
        if(offset.orElse(1)>totalPage.get()){
            currentPage = totalPage.get();
        }

        Page<BrandDTO> brandDTOPage =  brandServ.getBrandByPage(currentPage, size);
        int totalPages = brandDTOPage.getTotalPages();
        model.addAttribute("brandDTOPage", brandDTOPage);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageNumbers", paginationService.getPageNumber(totalPages, currentPage));
        return "brands";
    }
}
