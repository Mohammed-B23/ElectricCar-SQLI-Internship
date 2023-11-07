package com.sue.cars.service;

import com.sue.cars.dtos.StateDTO;
import com.sue.cars.dtos.diplay.DisplayStateDTO;
import com.sue.cars.entity.State;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StateService extends ServiceCRUD{
    DisplayStateDTO getState(String name);
    Page<State> getPageOfStates(int offset, int pageSize);
    Page<DisplayStateDTO> searchStateBySound(String name, int offset, int pageSize);

}
