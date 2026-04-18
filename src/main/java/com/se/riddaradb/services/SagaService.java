package com.se.riddaradb.services;

import com.se.riddaradb.dtos.SagaRequestDto;
import com.se.riddaradb.dtos.SagaResponseDto;
import com.se.riddaradb.mappers.SagaMapper;
import com.se.riddaradb.repositories.*;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class SagaService {

    final SagaRepository sagaRepository;
    final SagaMapper sagaMapper;
    final SagaVersionRepository sagaVersionRepository;

    public SagaService(SagaRepository sagaRepository,
                       SagaMapper sagaMapper,
                       SagaVersionRepository sagaVersionRepository) {

        this.sagaRepository = sagaRepository;
        this.sagaMapper = sagaMapper;
        this.sagaVersionRepository = sagaVersionRepository;
    }

    public Collection<SagaResponseDto> getSagas(){
        return sagaRepository.findAll()
                .stream()
                .map(sagaMapper::mapToDto)
                .toList();
    }

    public SagaResponseDto getSagaById(int id){
        if (sagaRepository.findById(id).isPresent()){
            return sagaMapper.mapToDto(sagaRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public SagaResponseDto saveSaga(SagaRequestDto sagaRequestDto){

//        SagaEntity sagaEntity = new SagaEntity(1, "saga title", "saga description");
//        SagaVersionEntity sagaVersionEntity = new SagaVersionEntity(1, "saga version title", "saga version description", 1300, true);
//        sagaVersionEntity.setSagaEntity(sagaEntity);
//
//        sagaRepository.save(sagaEntity);
//        sagaVersionRepository.save(sagaVersionEntity);

          return sagaMapper.mapToDto(sagaRepository.save(sagaMapper.mapFromDto(sagaRequestDto)));
    }

    public void deleteSagaById(int id) {

        if (sagaRepository.existsById(id)){
            sagaRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }
}
