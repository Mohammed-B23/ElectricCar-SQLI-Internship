package com.sue.cars.controller.thymeleaf;

import com.sqli.phonetic.services.GenericServ;
import com.sue.cars.dtos.ModelBrandDTO;
import com.sue.cars.dtos.diplay.DisplayCarDTO;
import com.sue.cars.entity.Brand;
import com.sue.cars.entity.Car;
import com.sue.cars.entity.ModelBrand;
import com.sue.cars.mappers.BrandMapper;
import com.sue.cars.mappers.CarMapper;
import com.sue.cars.mappers.ModelBrandMapper;
import com.sue.cars.service.CountryService;
import com.sue.cars.service.ModelBrandService;
import com.sue.cars.service.brandService;
import com.sue.cars.service.carService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class ViewCar {

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
    private final RestTemplate restTemplate;
    public ViewCar(carService carServ, RestTemplate restTemplate, CountryService countryServ, ModelBrandService modelBrandService) {
        this.carServ = carServ;
        this.restTemplate = restTemplate;
        this.countryServ = countryServ;
        this.modelBrandService = modelBrandService;
    }

    @GetMapping("/viewCars")
    public String viewCars(Model model, @RequestParam("offset") Optional<Integer> offset, @RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("totalpages") Optional<Integer> totalpages) {
        int currentPage = offset.orElse(1);
        if(currentPage==0){
            System.out.println("equal zero");
            currentPage = 1;
        }
        if (totalpages.isPresent()) {
            if(currentPage>totalpages.get()){
                currentPage = totalpages.get();
            }
        }


        int pgSize = pageSize.orElse(20);
        Page<Car> carPage = carServ.getCarsByPage(currentPage-1, pgSize);
        List<DisplayCarDTO> cars = carPage.getContent().stream().map(car -> carMapper.carToDisCarDto(car)).collect(Collectors.toList());
        model.addAttribute("cars", cars);
        model.addAttribute("carPage", carPage);
        System.out.println("currentPage : "+currentPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalpages", totalpages);
        model.addAttribute("number",(currentPage-1)*pgSize);
        int totalPages = carPage.getTotalPages();
        System.out.println("total pages = "+totalPages);
        List<Integer> pageNumbers;
        if (totalPages > 0) {

            if (totalPages <= 10) {
                pageNumbers = IntStream.rangeClosed(1,totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                System.out.println("nbr of pages :"+pageNumbers);

            }else{
                // if we have mor than 10 pages
                if(currentPage==totalPages){
                    pageNumbers = IntStream.rangeClosed(currentPage,currentPage)
                            .boxed()
                            .collect(Collectors.toList());
                }else {
                    if((totalPages-currentPage>=10)){
                        pageNumbers = IntStream.rangeClosed(currentPage,currentPage+ 9)
                                .boxed()
                                .collect(Collectors.toList());
                    }else {
                        System.out.println("yes");
                        pageNumbers = IntStream.rangeClosed(currentPage, totalPages)
                                .boxed()
                                .collect(Collectors.toList());
                    }

                }


            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "cars";

    }

    @GetMapping("/viewCar")
    public String viewCar(Model model, @RequestParam("id") Optional<Long> id){
        model.addAttribute("car",carServ.getById(id.orElse(0L)));
        return "viewCar";
    }

    @GetMapping("/searchCars")
    public String searchCars(Model model, @RequestParam(name = "value") String value, @RequestParam Optional<Integer> offset, @RequestParam Optional<Integer> pageSize,  @RequestParam Optional<Integer> totalpages) {
        List<DisplayCarDTO> cars;
        Map<String, String> columnsCar = new HashMap<>();

        columnsCar.put("vin",value);
        columnsCar.put("vehicleLocation",value);
        columnsCar.put("electricType",value);
        columnsCar.put("eligibility",value);
        columnsCar.put("electricUtility",value);

        GenericServ<Car> genericServ = new GenericServ<>(em);
        cars = genericServ.soundex(Car.class, columnsCar).stream().map(car -> carMapper.carToDisCarDto(car)).collect(Collectors.toList());

        if(cars.size()==0){
            // search in brand entity
            Optional<List<Brand>> brands  = Optional.of(brandServ.getBrandBySoundex(value));
            if(brands.isPresent()){
                cars = brands.get().stream().map(brand -> modelBrandService.getModelBrandByBrand(brand))
                        .flatMap(List::stream)
                        .map(modelBrand -> modelBrandMapper.modelToModelBrandDTO(modelBrand))
                        .map(modelBrandDTO -> carServ.getCarByModelBrand(modelBrandDTO))
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
            }
        }

        if(cars.size()==0){
            // search in model brand entity
            Optional<List<ModelBrand>> modelBrands  = Optional.of(modelBrandService.getModelBrandBySoundex(value));
            if (modelBrands.isPresent()){
                List<ModelBrandDTO> modelBrandDTOS = modelBrands.get().stream().map(modelBrand -> modelBrandMapper.modelToModelBrandDTO(modelBrand)).collect(Collectors.toList());
                cars = modelBrandDTOS.stream().map(modelBrandDTO -> carServ.getCarByModelBrand(modelBrandDTO)).flatMap(List::stream).collect(Collectors.toList());
            }
        }
        int currentPage = offset.orElse(1);
        if(currentPage==0){
            System.out.println("equal zero");
            currentPage = 1;
        }
        if (totalpages.isPresent()) {
            if(currentPage>totalpages.get()){
                currentPage = totalpages.get();
            }
        }

        Page<DisplayCarDTO> carPage = carServ.getPageCarsByModelBran(cars,currentPage-1, pageSize.orElse(20));
        model.addAttribute("numberOfResult",cars.size());
        model.addAttribute("cars", carPage.getContent());

        int totalPages = carPage.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {

            if (totalPages <= 10) {
                pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                System.out.println("nbr of pages :" + pageNumbers);

            } else {
                // if we have mor than 10 pages
                if (currentPage == totalPages) {
                    pageNumbers = IntStream.rangeClosed(currentPage, currentPage)
                            .boxed()
                            .collect(Collectors.toList());
                } else {
                    if ((totalPages - currentPage >= 10)) {
                        pageNumbers = IntStream.rangeClosed(currentPage, currentPage + 9)
                                .boxed()
                                .collect(Collectors.toList());
                    } else {
                        System.out.println("yes");
                        pageNumbers = IntStream.rangeClosed(currentPage, totalPages)
                                .boxed()
                                .collect(Collectors.toList());
                    }

                }


            }

        }
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("carPage", carPage);
        model.addAttribute("value",value);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("number",(currentPage-1)*pageSize.orElse(20));
        model.addAttribute("search","search");
        return "cars";
    }

    @GetMapping("manageCars")
    public String manageCars(Model model){
        model.addAttribute("cars",carServ.getAllCars(0,20));
        return "manageCars";
    }

}
