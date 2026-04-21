package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.SagaRequestDto;
import com.se.riddaradb.dtos.SagaResponseDto;
import com.se.riddaradb.services.SagaService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class SagaController {

    final SagaService sagaService;
    public SagaController(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @GetMapping("/sagas/getsagas")
    Set<SagaResponseDto> getSagas(){
        return sagaService.getSagas();
    }

    @GetMapping("/sagas/getsagabyid/{id}")
    SagaResponseDto getSagaById(@PathVariable int id){
        return sagaService.getSagaById(id);
    }

    @PostMapping("/sagas/postsaga")
    SagaResponseDto postSaga(@RequestBody SagaRequestDto sagaRequestDto){
        return sagaService.saveSaga(sagaRequestDto);
    }

    @PutMapping("/sagas/putsaga")
    SagaResponseDto putSaga(@RequestBody SagaRequestDto sagaRequestDto){
        return sagaService.saveSaga(sagaRequestDto);
    }

    @DeleteMapping("/sagas/deletesaga/{id}")
    void deleteSaga(@PathVariable int id){
        sagaService.deleteSagaById(id);
    }
}
