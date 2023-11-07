package com.sue.cars.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CRUDController<T> {

    @GetMapping("/all")
    List<T> getAll();
    @GetMapping("/{id}")
    T getById(@PathVariable Long id);

    @PostMapping("/add")
    T add(@RequestBody T t);

    @PutMapping("/update")
    T update(@RequestBody T t);
    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);

}
