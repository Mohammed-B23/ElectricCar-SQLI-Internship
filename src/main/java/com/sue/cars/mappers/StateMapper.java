package com.sue.cars.mappers;

import com.sue.cars.dtos.CountryDTO;
import com.sue.cars.dtos.StateDTO;
import com.sue.cars.dtos.diplay.DisplayStateDTO;
import com.sue.cars.entity.State;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CountryMapper countryMapper;
    public DisplayStateDTO stateToStateDTO(State state){
        DisplayStateDTO displayStateDTO = modelMapper.map(state, DisplayStateDTO.class);
        return displayStateDTO;
    }

    public State stateDtoToState(StateDTO stateDTO){
        State state = modelMapper.map(stateDTO, State.class);
        return state;
    }
}
