package com.se.riddaradb.services;

import com.se.riddaradb.dtos.MsDto;
import com.se.riddaradb.entities.MsEntity;
import com.se.riddaradb.entities.SagaEntity;
import com.se.riddaradb.mappers.MsMapper;
import com.se.riddaradb.repositories.MsRepository;
import com.se.riddaradb.repositories.SagaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MsService {

    final MsRepository msRepository;
    final SagaRepository sagaRepository;
    final MsMapper msMapper;

    public MsService(MsRepository msRepository, SagaRepository sagaRepository, MsMapper msMapper) {
        this.msRepository = msRepository;
        this.sagaRepository = sagaRepository;
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
        msEntity.setSagaEntity(new HashSet<>(sagaRepository.findAllById(msDto.getSagaIds())));
        return msMapper.mapToDto(msRepository.save(msEntity));
    }

    public void deleteMsEntryById(int id){

        removeMsFromSagaEntries(id);

        msRepository.deleteById(id);
    }

    private void removeMsFromSagaEntries(int id){
        //Stores each saga in database.
        Set<SagaEntity> sagaEntities = new HashSet<SagaEntity>(sagaRepository.findAll());
        //For each saga in database...
        for(SagaEntity saga : sagaEntities){
            Set<MsEntity> sagaMsEntity = new HashSet<MsEntity>(saga.getMsEntity());
            //get the bibliography entries for that saga.
            for(MsEntity msEntity : sagaMsEntity){
                //if a bibliography entry matches the ID supplied, remove it from the saga.
                if (msEntity.getId() == id) {
                    sagaMsEntity.remove(msEntity);
                    saga.setMsEntity(sagaMsEntity);
                    sagaRepository.save(saga);
                }
            }
        }
    }
}
