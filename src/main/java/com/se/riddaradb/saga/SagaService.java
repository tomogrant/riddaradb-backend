package com.se.riddaradb.saga;

import com.se.riddaradb.bib.BibRepository;
import com.se.riddaradb.sagaversion.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SagaService {

    final SagaRepository sagaRepository;
    final SagaMapper sagaMapper;
    final SagaVersionRepository sagaVersionRepository;
    final SagaVersionService sagaVersionService;
    final SagaVersionMapper sagaVersionMapper;
    final SagaVersionMotifRepository sagaVersionMotifRepository;
    final BibRepository bibRepository;

    public SagaService(SagaRepository sagaRepository,
                       SagaMapper sagaMapper,
                       SagaVersionRepository sagaVersionRepository,
                       SagaVersionService sagaVersionService,
                       SagaVersionMapper sagaVersionMapper,
                       SagaVersionMotifRepository sagaVersionMotifRepository,
                       BibRepository bibRepository) {

        this.sagaRepository = sagaRepository;
        this.sagaMapper = sagaMapper;
        this.sagaVersionRepository = sagaVersionRepository;
        this.sagaVersionService = sagaVersionService;
        this.sagaVersionMapper = sagaVersionMapper;
        this.sagaVersionMotifRepository = sagaVersionMotifRepository;
        this.bibRepository = bibRepository;
    }

    public Set<SagaResponseDto> getSagas(){
        return sagaRepository.findAll()
                .stream()
                .map(sagaMapper::mapToResponseDto)
                .collect(Collectors.toSet());
    }

    public SagaResponseDto getSagaById(int id){
        if (sagaRepository.findById(id).isPresent()){
            return sagaMapper.mapToResponseDto(sagaRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public Set<SagaTitleDto> getSagaTitles(){
        return sagaRepository.findAll()
                .stream()
                .map(sagaMapper::mapToSagaTitle)
                .collect(Collectors.toSet());
    }

    public SagaResponseDto saveSaga(SagaRequestDto sagaRequestDto){

        Set<Integer> currentSagaVersions = sagaRepository.findById(sagaRequestDto.getId()).map(saga ->
            saga.getSagaVersionEntities()
                    .stream()
                    .map(SagaVersionEntity::getId)
                    .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());

        Set<Integer> newSagaVersions = sagaRequestDto.getSagaVersions()
                .stream()
                .map(SagaVersionRequestDto::getId)
                .collect(Collectors.toSet());

        SagaEntity sagaEntity = sagaRepository.save(sagaMapper.mapToEntity(sagaRequestDto));
        sagaEntity.setBibEntity(new HashSet<>(bibRepository.findAllById(sagaRequestDto.getBibIds())));

        sagaRequestDto.getSagaVersions()
                .stream()
                .map(sagaVersionMapper::mapFromDto)
                .forEach(sagaVersionEntity -> {
                    sagaVersionEntity.setSagaVersionMotifEntities(sagaVersionMotifRepository.findBySagaVersionEntityId(sagaVersionEntity.getId()));
                    sagaEntity.addSagaVersion(sagaVersionEntity);
                });

        for (int sagaVersionId : currentSagaVersions){
            if (!newSagaVersions.contains(sagaVersionId)){
                sagaEntity.removeSagaVersion(sagaVersionId);
            }
        }

        return sagaMapper.mapToResponseDto(sagaRepository.save(sagaEntity));
    }

    public SagaResponseDto saveSagaWithVersion(SagaRequestDto sagaRequestDto){

        SagaVersionEntity sagaVersionEntity = new SagaVersionEntity(0, sagaRequestDto.getTitle(), "", SagaVersionEntity.SagaDate.UNDEFINED);

        SagaEntity sagaEntity = sagaMapper.mapToEntity(sagaRequestDto);
        sagaEntity.addSagaVersion(sagaVersionEntity);

        return sagaMapper.mapToResponseDto(sagaRepository.save(sagaEntity));
    }

    public void deleteSagaById(int id) {

        if (sagaRepository.findById(id).isPresent()){
            SagaEntity sagaEntity = sagaRepository.findById(id).get();
            for (SagaVersionEntity sagaVersionEntity : sagaEntity.getSagaVersionEntities()){
                sagaVersionService.deleteSagaVersionById(sagaVersionEntity.getId());
            }

            removeSagaFromBibEntries(id);

            sagaRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }

    private void removeSagaFromBibEntries(int id){
        for (SagaEntity saga : sagaRepository.findAll()) {
            saga.getBibEntity().removeIf(bib -> bib.getId() == id);
        }
    }
}
