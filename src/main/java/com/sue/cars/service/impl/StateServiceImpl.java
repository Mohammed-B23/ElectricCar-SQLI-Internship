package com.sue.cars.service.impl;

import com.sqli.phonetic.services.GenericServ;
import com.sue.cars.dtos.StateDTO;
import com.sue.cars.dtos.diplay.DisplayCarDTO;
import com.sue.cars.dtos.diplay.DisplayStateDTO;
import com.sue.cars.entity.Car;
import com.sue.cars.entity.Country;
import com.sue.cars.entity.State;
import com.sue.cars.mappers.StateMapper;
import com.sue.cars.repository.countryRepository;
import com.sue.cars.repository.stateRepository;
import com.sue.cars.service.StateService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {

    private final stateRepository stateRep;
    private final countryRepository countryRep;
    private final StateMapper stateMapper;
    @PersistenceContext
    private EntityManager em;
    public StateServiceImpl(stateRepository stateRep, countryRepository countryRep, StateMapper stateMapper) {
        this.stateRep = stateRep;
        this.countryRep = countryRep;
        this.stateMapper = stateMapper;
    }

    @Override
    public List<DisplayStateDTO> getAll() {
        return stateRep.findAll().stream().map(state -> stateMapper.stateToStateDTO(state))
                .collect(Collectors.toList());
    }

    @Override
    public DisplayStateDTO getById(Long id) {
        Optional<State> state = stateRep.findById(id);
        if(state.isPresent()){
            return stateMapper.stateToStateDTO(state.get());
        }
        return null;
    }

    @Override
    public DisplayStateDTO getState(String name) {
         Optional<State> state = stateRep.findByName(name);
         if(state.isPresent()){
             return stateMapper.stateToStateDTO(state.get());
         }
         return null;
    }

    @Override
    public Page<State> getPageOfStates(int offset, int pageSize) {
//        List<DisplayStateDTO> dtos = stateRep.findAll(PageRequest.of(offset-1, pageSize))
//                .stream()
//                .map(state -> stateMapper.stateToStateDTO(state))
//                .collect(Collectors.toList());
//        return new PageImpl<>(dtos);
        return stateRep.findAll(PageRequest.of(offset-1, pageSize));
    }

    @Override
    public Page<DisplayStateDTO> searchStateBySound(String name, int offset, int pageSize) {
        List<DisplayStateDTO> stateDTOS;
        Map<String, String> columnsState = new HashMap<>();
        columnsState.put("name",name);
        GenericServ<State> genericServ = new GenericServ<>(em);
        stateDTOS = genericServ.soundex(State.class, columnsState)
                .stream()
                .map(state -> stateMapper.stateToStateDTO(state))
                .collect(Collectors.toList());

        Pageable pageRequest = PageRequest.of(offset-1,pageSize);
        int start = (int) pageRequest.getOffset();
        int end = Math.min( (start + pageRequest.getPageSize()), stateDTOS.size() );
        List<DisplayStateDTO> pageContent = stateDTOS.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, stateDTOS.size());
    }

    @Override
    public DisplayStateDTO addEntity(Object stateObject) {
        StateDTO stateDTO = (StateDTO) stateObject;
            State state = stateMapper.stateDtoToState(stateDTO);
            return stateMapper.stateToStateDTO(stateRep.save(state));
    }

    @Override
    public DisplayStateDTO updateEntity(Object stateObject) {
        StateDTO stateDTO = (StateDTO) stateObject;
        if(stateRep.findById(stateDTO.getId()).isPresent()){
            State state = stateMapper.stateDtoToState(stateDTO);
            return stateMapper.stateToStateDTO(stateRep.save(state));
        }
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<State> state = stateRep.findById(id);
        if(state.isPresent())
            stateRep.delete(state.get());
        else
            System.out.println("this state is not exist!");
    }
}
