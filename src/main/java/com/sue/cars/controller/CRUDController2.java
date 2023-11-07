package com.sue.cars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CRUDController2<T> extends CRUDController{
    @GetMapping
    T getByName(@RequestParam(value = "name") String name);
}
