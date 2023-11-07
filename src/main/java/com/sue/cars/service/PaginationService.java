package com.sue.cars.service;

import java.util.List;

public interface PaginationService {
    List<Integer> getPageNumber(Integer totalPages, Integer currentPage);
}
