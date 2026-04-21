package com.se.riddaradb.services;

import com.se.riddaradb.dtos.SagaRequestDto;
import com.se.riddaradb.dtos.SagaResponseDto;
import com.se.riddaradb.entities.SagaEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.SagaMapper;
import com.se.riddaradb.repositories.*;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SagaService {

    final SagaRepository sagaRepository;
    final SagaMapper sagaMapper;
    final SagaVersionRepository sagaVersionRepository;
    final SagaVersionService sagaVersionService;

    public SagaService(SagaRepository sagaRepository,
                       SagaMapper sagaMapper,
                       SagaVersionRepository sagaVersionRepository,
                       SagaVersionService sagaVersionService) {

        this.sagaRepository = sagaRepository;
        this.sagaMapper = sagaMapper;
        this.sagaVersionRepository = sagaVersionRepository;
        this.sagaVersionService = sagaVersionService;
    }

    public Set<SagaResponseDto> getSagas(){
        return sagaRepository.findAll()
                .stream()
                .map(sagaMapper::mapToDto)
                .collect(Collectors.toSet());
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
        return sagaMapper.mapToDto(sagaRepository.save(sagaMapper.mapFromDto(sagaRequestDto)));

//        SagaEntity sagaEntity = new SagaEntity(1, "saga title", "saga description");
//        SagaVersionEntity sagaVersionEntity = new SagaVersionEntity(1, "saga version title", "saga version description", 1300, true);
//        sagaVersionEntity.setSagaEntity(sagaEntity);
//
//        sagaRepository.save(sagaEntity);
//        sagaVersionRepository.save(sagaVersionEntity);

          //return sagaMapper.mapToDto(sagaRepository.save(sagaMapper.mapFromDto(sagaRequestDto)));
    }

    public void deleteSagaById(int id) {

        if (sagaRepository.findById(id).isPresent()){
            SagaEntity sagaEntity = sagaRepository.findById(id).get();
            for (SagaVersionEntity sagaVersionEntity : sagaEntity.getSagaVersionEntities()){
                sagaVersionService.deleteSagaVersionById(sagaVersionEntity.getId());
            }

            sagaRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }
}
