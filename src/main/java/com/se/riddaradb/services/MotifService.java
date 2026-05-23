package com.se.riddaradb.services;

import com.se.riddaradb.dtos.MotifDto;
import com.se.riddaradb.entities.MotifEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.MotifMapper;
import com.se.riddaradb.repositories.MotifRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MotifService {

    final MotifRepository motifRepository;
    final SagaVersionRepository sagaVersionRepository;
    final MotifMapper motifMapper;
    final EntityManager entityManager;

    public MotifService(MotifRepository motifRepository, SagaVersionRepository sagaVersionRepository, MotifMapper motifMapper, EntityManager entityManager) {
        this.motifRepository = motifRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.motifMapper = motifMapper;
        this.entityManager = entityManager;
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

        motifRepository.findById(motifDto.getParentId()).ifPresent(parentMotif ->
            parentMotif.addChildMotif(motifEntity));

        //This DTO can never have 'hasChildren' set to true, as
        //the motif entity passed in is mapped from a DTO without
        //a child field. This is accounted for in the frontend, but
        //it might be worth changing this somehow.
        return motifMapper.mapToDto(motifRepository.save(motifEntity));
    }

    public void deleteMotifEntryById(int id){

        MotifEntity motifEntity = motifRepository.findById(id).orElseThrow();

        if (motifEntity.getParent() != null){
            MotifEntity motifParent = motifRepository.findById(motifEntity.getParent().getId()).orElseThrow();
            motifParent.getChildren().removeIf(child -> child.getId() == id);
        }

        removeChildren(id);

        motifRepository.deleteById(id);
    }

    void removeChildren(int id){
        motifRepository.findById(id).ifPresent(motif -> {
            motif.getChildren().forEach((child -> removeChildren(child.getId())));
        });

        motifRepository.deleteById(id);
    }

    private void removeMotifFromSagaEntries(int id){
    }
}
