package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.SagaVersionRequestDto;
import com.se.riddaradb.dtos.SagaVersionResponseDto;
import com.se.riddaradb.services.SagaVersionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
public class SagaVersionController {

    final SagaVersionService sagaVersionService;
    public SagaVersionController(SagaVersionService sagaVersionService) {
        this.sagaVersionService = sagaVersionService;
    }

    @GetMapping("/sagas/getsagaversions")
    Set<SagaVersionResponseDto> getSagas(){
        return sagaVersionService.getSagaVersions();
    }

    @GetMapping("/sagas/getsagaversionbyid/{id}")
    SagaVersionResponseDto getSagaById(@PathVariable int id){
        return sagaVersionService.getSagaVersionById(id);
    }

    @PostMapping("/sagas/postsagaversion")
    SagaVersionResponseDto postSagas(@RequestBody SagaVersionRequestDto sagaVersionRequestDto){
        return sagaVersionService.saveSagaVersion(sagaVersionRequestDto);
    }

    @PutMapping("/sagas/putsagaversion")
    SagaVersionResponseDto putSaga(@RequestBody SagaVersionRequestDto sagaVersionRequestDto){
        return sagaVersionService.saveSagaVersion(sagaVersionRequestDto);
    }

    @DeleteMapping("/sagas/deletesagaversion/{id}")
    void deleteSaga(@PathVariable int id){
        sagaVersionService.deleteSagaVersionById(id);
    }
}
