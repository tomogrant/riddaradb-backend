package com.se.riddaradb.services;

import com.se.riddaradb.dtos.ObjectDto;
import com.se.riddaradb.entities.ObjectEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.ObjectMapper;
import com.se.riddaradb.repositories.ObjectRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ObjectService {

    final ObjectRepository objectRepository;
    final SagaVersionRepository sagaVersionRepository;
    final ObjectMapper objectMapper;

    public ObjectService(ObjectRepository objectRepository, SagaVersionRepository sagaVersionRepository, ObjectMapper objectMapper) {
        this.objectRepository = objectRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.objectMapper = objectMapper;
    }

    public Collection<ObjectDto> getObjectEntries(){
        return objectRepository.findAll()
                .stream()
                .map(objectMapper::mapToDto)
                .toList();
    }

    public ObjectDto getObjectEntryById(int id){
        if (objectRepository.findById(id).isPresent()){
            return objectMapper.mapToDto(objectRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public ObjectDto saveObjectEntry(ObjectDto objectDto){
        ObjectEntity objectEntity = objectMapper.mapFromDto(objectDto);
        objectEntity.setSagaVersionEntity(new HashSet<>(sagaVersionRepository.findAllById(objectDto.getSagaVersionIds())));
        return objectMapper.mapToDto(objectRepository.save(objectEntity));
    }

    public void deleteObjectEntryById(int id){

        removeObjectFromSagaEntries(id);

        objectRepository.deleteById(id);
    }

    private void removeObjectFromSagaEntries(int id){
        //Stores each saga in database.
        Set<SagaVersionEntity> sagaEntities = new HashSet<SagaVersionEntity>(sagaVersionRepository.findAll());
        //For each saga in database...
        for(SagaVersionEntity saga : sagaEntities){
            Set<ObjectEntity> sagaObjectEntity = new HashSet<ObjectEntity>(saga.getObjectEntity());
            //get the bibliography entries for that saga.
            for(ObjectEntity objectEntity : sagaObjectEntity){
                //if a bibliography entry matches the ID supplied, remove it from the saga.
                if (objectEntity.getId() == id) {
                    sagaObjectEntity.remove(objectEntity);
                    saga.setObjectEntity(sagaObjectEntity);
                    sagaVersionRepository.save(saga);
                }
            }
        }
    }
}
