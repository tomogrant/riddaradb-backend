package com.se.riddaradb.services;

import com.se.riddaradb.dtos.MsDto;
import com.se.riddaradb.entities.MsEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.MsMapper;
import com.se.riddaradb.repositories.MsRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MsService {

    final MsRepository msRepository;
    final SagaVersionRepository sagaVersionRepository;
    final MsMapper msMapper;

    public MsService(MsRepository msRepository, SagaVersionRepository sagaVersionRepository, MsMapper msMapper) {
        this.msRepository = msRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.msMapper = msMapper;
    }

    public Collection<MsDto> getMsEntries(){
        return msRepository.findAll()
                .stream()
                .map(msMapper::mapToDto)
                .toList();
    }

    public MsDto getMsEntryById(int id){
        if (msRepository.findById(id).isPresent()){
            return msMapper.mapToDto(msRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public MsDto saveMsEntry(MsDto msDto){
        MsEntity msEntity = msMapper.mapFromDto(msDto);
        msEntity.setSagaVersionEntity(new HashSet<>(sagaVersionRepository.findAllById(msDto.getSagaVersionIds())));
        return msMapper.mapToDto(msRepository.save(msEntity));
    }

    public void deleteMsEntryById(int id){

        removeMsFromSagaEntries(id);

        msRepository.deleteById(id);
    }

    private void removeMsFromSagaEntries(int id){
        //Stores each saga in database.
        Set<SagaVersionEntity> sagaEntities = new HashSet<SagaVersionEntity>(sagaVersionRepository.findAll());
        //For each saga in database...
        for(SagaVersionEntity saga : sagaEntities){
            Set<MsEntity> sagaMsEntity = new HashSet<MsEntity>(saga.getMsEntity());
            //get the bibliography entries for that saga.
            for(MsEntity msEntity : sagaMsEntity){
                //if a bibliography entry matches the ID supplied, remove it from the saga.
                if (msEntity.getId() == id) {
                    sagaMsEntity.remove(msEntity);
                    saga.setMsEntity(sagaMsEntity);
                    sagaVersionRepository.save(saga);
                }
            }
        }
    }
}
