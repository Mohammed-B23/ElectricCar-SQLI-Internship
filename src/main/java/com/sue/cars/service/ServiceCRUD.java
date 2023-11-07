package com.sue.cars.service;

import java.util.List;

public interface ServiceCRUD<T> {
    List<T> getAll();
    T getById(Long id);
    T addEntity(T t);
    T updateEntity(T t);
    void deleteEntity(Long id);
}
