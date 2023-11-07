package com.sue.cars.controller.thymeleaf;

import com.sue.cars.dtos.CountryDTO;
import com.sue.cars.dtos.diplay.DisplayCarDTO;
import com.sue.cars.dtos.diplay.DisplayCityDTO;
import com.sue.cars.entity.Car;
import com.sue.cars.entity.City;
import com.sue.cars.mappers.CityMapper;
import com.sue.cars.service.CityService;
import com.sue.cars.service.CountryService;
import com.sue.cars.service.carService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MapController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CountryService countryService;
    @Autowired
    private carService carService;

    @GetMapping("/showMap")
    public String index(Model model, @RequestParam Optional<Long> idState) {
        long id = idState.orElse(1L);
        System.out.println("idState = "+id);
        List<String> list = carService.getCarsByState(id);
        System.out.println("length of list = "+list.size());
        model.addAttribute("locations", list);

        return "index2";
    }
}
