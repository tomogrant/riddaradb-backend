package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.SagaVersionDto;
import com.se.riddaradb.services.SagaVersionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class SagaVersionController {

    final SagaVersionService sagaVersionService;
    public SagaVersionController(SagaVersionService sagaVersionService) {
        this.sagaVersionService = sagaVersionService;
    }

    @GetMapping("/sagas/getsagaversions")
    Collection<SagaVersionDto> getSagas(){
        return sagaVersionService.getSagaVersions();
    }

    @GetMapping("/sagas/getsagaversionbyid/{id}")
    SagaVersionDto getSagaById(@PathVariable int id){
        return sagaVersionService.getSagaVersionById(id);
    }

    @PostMapping("/sagas/postsagaversion")
    SagaVersionDto postSagas(@RequestBody SagaVersionDto sagaVersionDto){
        return sagaVersionService.saveSagaVersion(sagaVersionDto);
    }

    @PutMapping("/sagas/putsagaversion")
    SagaVersionDto putSaga(@RequestBody SagaVersionDto sagaVersionDto){
        return sagaVersionService.saveSagaVersion(sagaVersionDto);
    }

    @DeleteMapping("/sagas/deletesagaversion/{id}")
    void deleteSaga(@PathVariable int id){
        sagaVersionService.deleteSagaVersionById(id);
    }
}
