package com.se.riddaradb.services;

import com.se.riddaradb.dtos.MotifDto;
import com.se.riddaradb.entities.MotifEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.MotifMapper;
import com.se.riddaradb.repositories.MotifRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MotifService {

    final MotifRepository motifRepository;
    final SagaVersionRepository sagaVersionRepository;
    final MotifMapper motifMapper;

    public MotifService(MotifRepository motifRepository, SagaVersionRepository sagaVersionRepository, MotifMapper motifMapper) {
        this.motifRepository = motifRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.motifMapper = motifMapper;
    }

    public Collection<MotifDto> getMotifEntries(){
        return motifRepository.findAll()
                .stream()
                .map(motifMapper::mapToDto)
                .toList();
    }

    public MotifDto getMotifEntryById(int id){
        if (motifRepository.findById(id).isPresent()){
            return motifMapper.mapToDto(motifRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public Collection<MotifDto> getRootMotifs(){
        return motifRepository.findByParentIsNull()
                .stream()
                .map(motifMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    public Collection<MotifDto> getChildMotifs(int parentId){
        Set<MotifDto> childMotifs = new HashSet<>();

        if (motifRepository.findById(parentId).isPresent()){
            childMotifs = motifRepository.findById(parentId).get().getChildren()
                    .stream()
                    .map(motifMapper::mapToDto)
                    .collect(Collectors.toSet());
        }

        return childMotifs;
    }

    public MotifDto saveMotifEntry(MotifDto motifDto){

        MotifEntity motifEntity = motifMapper.mapFromDto(motifDto);

        motifRepository.findById(motifDto.getParentId()).ifPresent(parentMotif -> {
            parentMotif.addChildMotif(motifEntity);
        });

        return motifMapper.mapToDto(motifRepository.save(motifEntity));
    }

    public void deleteMotifEntryById(int id){

        removeMotifFromSagaEntries(id);

        motifRepository.deleteById(id);
    }

    private void removeMotifFromSagaEntries(int id){
        //Stores each saga in database.
        Set<SagaVersionEntity> sagaEntities = new HashSet<SagaVersionEntity>(sagaVersionRepository.findAll());
        //For each saga in database...
        for(SagaVersionEntity saga : sagaEntities){
            Set<MotifEntity> sagaMotifEntity = new HashSet<MotifEntity>(saga.getMotifEntity());
            //get the bibliography entries for that saga.
            for(MotifEntity motifEntity : sagaMotifEntity){
                //if a bibliography entry matches the ID supplied, remove it from the saga.
                if (motifEntity.getId() == id) {
                    sagaMotifEntity.remove(motifEntity);
                    saga.setMotifEntity(sagaMotifEntity);
                    sagaVersionRepository.save(saga);
                }
            }
        }
    }
}
