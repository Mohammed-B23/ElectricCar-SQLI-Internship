package com.sue.cars.controller.thymeleaf;

import com.sue.cars.dtos.diplay.DisplayStateDTO;
import com.sue.cars.entity.State;
import com.sue.cars.service.PaginationService;
import com.sue.cars.service.StateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ViewState {
    @Autowired
    private StateService stateService;
    @Autowired
    private PaginationService paginationService;

    @GetMapping("/viewStates")
    public String viewStates(Model model, @RequestParam Optional<Integer> offSet,@RequestParam Optional<Integer> pageSize, @RequestParam("totalpages") Optional<Integer> totalpages) {
        int currentPage = offSet.orElse(1);
        int size = pageSize.orElse(20);

        if(currentPage==0){
            System.out.println("equal zero");
            currentPage = 1;
        }
        if (totalpages.isPresent()) {
            if(currentPage>totalpages.get()){
                currentPage = totalpages.get();
            }
        }

        Page<State> statePage = stateService.getPageOfStates(currentPage, size);
        model.addAttribute("statePage", statePage);

        int totalPages = statePage.getTotalPages();
        System.out.println("totalPages "+totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalpages", totalpages);
        model.addAttribute("number",(currentPage-1)*size);
        model.addAttribute("pageNumbers", paginationService.getPageNumber(totalPages, currentPage));

        return "states";
    }

    @GetMapping("/searchState")
    public String searchState(Model model, @RequestParam(name = "stateName") Optional<String> optName, @RequestParam Optional<Integer> offset, @RequestParam Optional<Integer> pageSize, @RequestParam Optional<Integer> totalpages){
        int currentPage = offset.orElse(1);
        int size = pageSize.orElse(20);
        String name = optName.orElse("WA");
        Page<DisplayStateDTO> statePage =  stateService.searchStateBySound(name, currentPage,size);
        model.addAttribute("statePage",statePage);

        int totalPages = statePage.getTotalPages();
        System.out.println("totalPages "+totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalpages", totalpages);
        model.addAttribute("number",(currentPage-1)*size);
        model.addAttribute("search","search");


        List<Integer> pageNumbers;
        if (totalPages > 0) {
            if (totalPages <= 10) {
                pageNumbers = IntStream.rangeClosed(1,totalPages)
                        .boxed()
                        .collect(Collectors.toList());
            }else{
                // if we have mor than 10 pages
                if(currentPage==totalPages){
                    pageNumbers = IntStream.rangeClosed(currentPage, currentPage)
                            .boxed()
                            .collect(Collectors.toList());
                }else {
                    if((totalPages-currentPage>=10)){
                        pageNumbers = IntStream.rangeClosed(currentPage, currentPage+ 9)
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

        return "states";
    }
}
