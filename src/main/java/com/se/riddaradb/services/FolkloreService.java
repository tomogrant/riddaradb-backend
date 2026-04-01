package com.se.riddaradb.services;

import com.se.riddaradb.dtos.FolkloreDto;
import com.se.riddaradb.entities.FolkloreEntity;
import com.se.riddaradb.entities.SagaEntity;
import com.se.riddaradb.mappers.FolkloreMapper;
import com.se.riddaradb.repositories.FolkloreRepository;
import com.se.riddaradb.repositories.SagaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class FolkloreService {

    final FolkloreRepository folkloreRepository;
    final SagaRepository sagaRepository;
    final FolkloreMapper folkloreMapper;

    public FolkloreService(FolkloreRepository folkloreRepository, SagaRepository sagaRepository, FolkloreMapper folkloreMapper) {
        this.folkloreRepository = folkloreRepository;
        this.sagaRepository = sagaRepository;
        this.folkloreMapper = folkloreMapper;
    }

    public Collection<FolkloreDto> getFolkloreEntries(){
        return folkloreRepository.findAll()
                .stream()
                .map(folkloreMapper::mapToDto)
                .toList();
    }

    public FolkloreDto getFolkloreEntryById(int id){
        if (folkloreRepository.findById(id).isPresent()){
            return folkloreMapper.mapToDto(folkloreRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public FolkloreDto saveFolkloreEntry(FolkloreDto folkloreDto){
        FolkloreEntity folkloreEntity = folkloreMapper.mapFromDto(folkloreDto);
        folkloreEntity.setSagaEntity(new HashSet<>(sagaRepository.findAllById(folkloreDto.getSagaIds())));
        return folkloreMapper.mapToDto(folkloreRepository.save(folkloreEntity));
    }

    public void deleteFolkloreEntryById(int id){

        removeFolkloreFromSagaEntries(id);

        folkloreRepository.deleteById(id);
    }

    private void removeFolkloreFromSagaEntries(int id){
        //Stores each saga in database.
        Set<SagaEntity> sagaEntities = new HashSet<SagaEntity>(sagaRepository.findAll());
        //For each saga in database...
        for(SagaEntity saga : sagaEntities){
            Set<FolkloreEntity> sagaFolkloreEntity = new HashSet<FolkloreEntity>(saga.getFolkloreEntity());
            //get the bibliography entries for that saga.
            for(FolkloreEntity folkloreEntity : sagaFolkloreEntity){
                //if a bibliography entry matches the ID supplied, remove it from the saga.
                if (folkloreEntity.getId() == id) {
                    sagaFolkloreEntity.remove(folkloreEntity);
                    saga.setFolkloreEntity(sagaFolkloreEntity);
                    sagaRepository.save(saga);
                }
            }
        }
    }
}
