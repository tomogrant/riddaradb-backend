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

    public SagaResponseDto updateSaga(SagaRequestDto sagaRequestDto){

        SagaEntity sagaEntity = sagaRepository.findById(sagaRequestDto.getId()).orElseThrow();

        sagaEntity.setTitle(sagaRequestDto.getTitle());
        sagaEntity.setDescription(sagaRequestDto.getDescription());
        sagaEntity.setTranslated(sagaRequestDto.getTranslated());

        Set<Integer> currentSagaVersions = sagaEntity.getSagaVersionEntities()
                    .stream()
                    .map(SagaVersionEntity::getId)
                    .collect(Collectors.toSet());

        Set<Integer> newSagaVersions = sagaRequestDto.getSagaVersions()
                .stream()
                .map(SagaVersionRequestDto::getId)
                .collect(Collectors.toSet());

        sagaEntity.setBibEntity(new HashSet<>(bibRepository.findAllById(sagaRequestDto.getBibIds())));

        //For an update, you can just get the saga version entities attached to the saga entity fetched above.
        for (SagaVersionRequestDto sagaVersionDto : sagaRequestDto.getSagaVersions()){
            //Incoming saga version lacks ID; add to saga version
            System.out.println("Saga version received has ID: " + sagaVersionDto.getId());

            if (sagaVersionDto.getId() == null){
                sagaEntity.addSagaVersion(new SagaVersionEntity(null, sagaVersionDto.getTitle(), sagaVersionDto.getDescription(), sagaVersionDto.getDate()));
            }
            else{
                //If saga version already present, update it
                SagaVersionEntity sagaVersionEntity =  sagaEntity.getSagaVersionEntities()
                        .stream()
                        .filter(entity -> entity.getId() == sagaVersionDto.getId())
                        .findFirst()
                        .orElseThrow();

                sagaVersionEntity.setTitle(sagaVersionDto.getTitle());
                sagaVersionEntity.setDescription(sagaVersionDto.getDescription());
                sagaVersionEntity.setDate(sagaVersionDto.getDate());
            }
        }

        for (int currentSagaVersionId : currentSagaVersions) {
            if (!newSagaVersions.contains(currentSagaVersionId)) {
                sagaEntity.removeSagaVersion(currentSagaVersionId);
            }
        }

        return sagaMapper.mapToResponseDto(sagaRepository.save(sagaEntity));
    }

    public SagaResponseDto saveSaga(SagaRequestDto sagaRequestDto){

        SagaEntity sagaEntity = new SagaEntity(null, sagaRequestDto.getTitle(), sagaRequestDto.getDescription(), sagaRequestDto.getTranslated());

        for (SagaVersionRequestDto sagaVersionRequestDto : sagaRequestDto.getSagaVersions()){
            sagaEntity.addSagaVersion(new SagaVersionEntity(null, sagaVersionRequestDto.getTitle(), sagaVersionRequestDto.getDescription(), sagaVersionRequestDto.getDate()));
        }

        return sagaMapper.mapToResponseDto(this.sagaRepository.save(sagaEntity));
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
