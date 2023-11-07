package com.sue.cars.controller.thymeleaf;

import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.dtos.diplay.DisplayModelBrand;
import com.sue.cars.entity.ModelBrand;
import com.sue.cars.mappers.BrandMapper;
import com.sue.cars.mappers.CarMapper;
import com.sue.cars.mappers.ModelBrandMapper;
import com.sue.cars.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class ViewModels {

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

    public ViewModels(carService carServ, RestTemplate restTemplate, CountryService countryServ, ModelBrandService modelBrandService) {
        this.carServ = carServ;
        this.restTemplate = restTemplate;
        this.countryServ = countryServ;
        this.modelBrandService = modelBrandService;
    }
    @GetMapping("/viewModels")
    public String viewModels(Model model, @RequestParam Optional<Integer> offset, @RequestParam Optional<Integer> pageSize, @RequestParam Optional<Integer> totalPages) {
        int currentPage = offset.orElse(1);
        if(offset.orElse(1)<=0){
            currentPage = 1;
        }

        if (totalPages.isPresent()){
            if (currentPage>totalPages.get())
                currentPage = totalPages.get();
        }

        Page<ModelBrand> modelBrandPage = modelBrandService.getModelBrandPage(currentPage-1, pageSize.orElse(20));
        List<DisplayModelBrand> models = modelBrandPage.getContent().stream().map(modelBrand -> modelBrandMapper.modelBrandToModelBrandDTO(modelBrand)).collect(Collectors.toList());
        model.addAttribute("modelBrandPage", modelBrandPage);
        model.addAttribute("currentPage", currentPage);
        int totalPage = modelBrandPage.getTotalPages();
        System.out.println("total model brand pages is :"+totalPages);
        model.addAttribute("pageNumbers",paginationService.getPageNumber(totalPage, currentPage));

        List<BrandDTO> brands =brandServ.getAll();
        List<Integer> modelYears = modelBrandService.gerDistinctModelYear();
        model.addAttribute("models", models);
        model.addAttribute("brands", brands);
        model.addAttribute("modelYears", modelYears);
        return "models";
    }

    @GetMapping("/searchModelBrandsByBrandAndYear")
    public String getModelBrandsByBrandAndYear(Model model, @RequestParam(name = "brandName") Optional<String> brandName,@RequestParam(name = "modelYear") Optional<Integer> modelYear) {
        System.out.println("brand name : "+brandName.get()+"model year :"+modelYear.get());
        BrandDTO brand = brandServ.getBrand(brandName.orElse("tesla"));
//        System.out.println("brand name "+brand);
        List<DisplayModelBrand> displayModelBrands = modelBrandService.getByModelYearAndBrand(brand,modelYear.orElse(2018));
        model.addAttribute("displayModelBrands", displayModelBrands);
        return "searchCarByModelBrand";
    }

    @GetMapping("/searchModel")
    public String searchModel(Model model, @RequestParam(name = "modelName") String name) {
        DisplayModelBrand modelBrand = modelBrandService.getModelBrand(name);
        model.addAttribute("result", modelBrand);
        return "searchModelBrand";
    }

    @GetMapping("viewModelBrands")
    public String viewModelBrands(Model model, @RequestParam Optional<Long> id){
        BrandDTO  brandDTO = (BrandDTO) brandServ.getById(id.orElse(0L));
        model.addAttribute("modelBrandList",modelBrandService.getModelBrandByBrand(brandMapper.BrandDtoToBrand(brandDTO))
                .stream()
                .map(modelBrand -> modelBrandMapper.modelBrandToModelBrandDTO(modelBrand)));
        return "viewModelBrands";
    }
}
