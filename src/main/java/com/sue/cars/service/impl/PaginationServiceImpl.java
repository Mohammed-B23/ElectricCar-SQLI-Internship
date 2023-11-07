package com.sue.cars.service.impl;

import com.sue.cars.service.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaginationServiceImpl implements PaginationService {

    @Override
    public List<Integer> getPageNumber(Integer totalPages, Integer currentPage) {
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
        return pageNumbers;
    }
}
