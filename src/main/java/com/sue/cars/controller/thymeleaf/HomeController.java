package com.sue.cars.controller.thymeleaf;

import com.sue.cars.dtos.BrandDTO;
import com.sue.cars.mappers.ModelBrandMapper;
import com.sue.cars.service.ModelBrandService;
import com.sue.cars.service.brandService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private brandService brandServ;
    @Autowired
    private ModelBrandMapper modelBrandMapper;
    @Autowired
    private ModelBrandService modelBrandService;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model, @RequestParam Optional<Integer> year, @RequestParam Optional<String> brandName) {
        int modelYear = year.orElse(2023);
        System.out.println("year = "+modelYear);
        String brandName1 = brandName.orElse("ford");

        List<BrandDTO> brands = brandServ.getAll();
        Map<String, Long> map = modelBrandService.getBrandsByModelYear(modelYear);
        Map<Integer, Long> map1 = modelBrandService.getModelYearsByBrand(brandName1);

        Set<String> keys = map.keySet();
        List<Long> values = map.values().stream().collect(Collectors.toList());
        Set<Integer> keys1 = map1.keySet();
        List<Long> values1 = map1.values().stream().collect(Collectors.toList());
        List<String> brandNames = brands.stream().map(brand -> brand.getName()).collect(Collectors.toList());
        List<Integer> modelYears = modelBrandService.gerDistinctModelYear();

        model.addAttribute("keys", keys);
        System.out.println("keys : "+keys);
        model.addAttribute("values",values);
        model.addAttribute("keys1", keys1);
        model.addAttribute("values1",values1);
        model.addAttribute("year", modelYear);
        model.addAttribute("brandName1",brandName1);
        model.addAttribute("modelYears", modelYears);
        model.addAttribute("brandNames",brandNames);
        model.addAttribute("request", request);
        return "home";
    }
}
