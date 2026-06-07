package com.se.riddaradb.saga;

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

    @GetMapping("/sagas/getsagatitles")
    Set<SagaTitleDto> getSagaTitles() { return sagaService.getSagaTitles();}

    @PostMapping("/sagas/postsaga")
    SagaResponseDto postSaga(@RequestBody SagaRequestDto sagaRequestDto){
        return sagaService.saveSaga(sagaRequestDto);
    }

    @PutMapping("/sagas/putsaga")
    SagaResponseDto putSaga(@RequestBody SagaRequestDto sagaRequestDto){
        return sagaService.updateSaga(sagaRequestDto);
    }

    @DeleteMapping("/sagas/deletesaga/{id}")
    void deleteSaga(@PathVariable int id){
        sagaService.deleteSagaById(id);
    }
}
