package com.se.riddaradb.saga;

import com.se.riddaradb.sagaversion.SagaVersionEntity;
import com.se.riddaradb.sagaversion.SagaVersionRepository;
import com.se.riddaradb.sagaversion.SagaVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
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
    }

    public SagaResponseDto saveSagaWithVersion(SagaRequestDto sagaRequestDto){

        SagaVersionEntity sagaVersionEntity = new SagaVersionEntity(0, sagaRequestDto.getTitle(), "", SagaVersionEntity.SagaDate.UNDEFINED);

        SagaEntity sagaEntity = sagaMapper.mapFromDto(sagaRequestDto);
        sagaEntity.addSagaVersion(sagaVersionEntity);

        return sagaMapper.mapToDto(sagaRepository.save(sagaEntity));
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
