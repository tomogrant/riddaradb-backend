package com.se.riddaradb.services;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.BibMapper;
import com.se.riddaradb.entities.BibEntity;
import com.se.riddaradb.repositories.BibRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class BibService {

    final BibRepository bibRepository;
    final SagaVersionRepository sagaVersionRepository;
    final BibMapper bibMapper;

    public BibService(BibRepository bibRepository, SagaVersionRepository sagaVersionRepository, BibMapper bibMapper) {
        this.bibRepository = bibRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.bibMapper = bibMapper;
    }

    public Collection<BibDto> getBibEntries(){
        return bibRepository.findAll()
                .stream()
                .map(bibMapper::mapToDto)
                .toList();
    }

    public BibDto getBibEntryById(int id){
        if (bibRepository.findById(id).isPresent()){
            return bibMapper.mapToDto(bibRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public BibDto saveBibEntry(BibDto bibDto){
        BibEntity bibEntity = bibMapper.mapFromDto(bibDto);
        bibEntity.setSagaVersionEntity(new HashSet<>(sagaVersionRepository.findAllById(bibDto.getSagaVersionIds())));
        return bibMapper.mapToDto(bibRepository.save(bibEntity));
    }

    public void deleteBibEntryById(int id){

        //Stores each saga in database.
        Set<SagaVersionEntity> sagaEntities = new HashSet<SagaVersionEntity>(sagaVersionRepository.findAll());

        //For each saga in database...
        for(SagaVersionEntity saga : sagaEntities){
            Set<BibEntity> sagaBibEntity = new HashSet<BibEntity>(saga.getBibEntity());
            //get the bibliography entries for that saga.
            for(BibEntity bibEntity : sagaBibEntity){
                //if a bibliography entry matches the ID supplied, remove it from the saga.
                if (bibEntity.getId() == id) {
                    sagaBibEntity.remove(bibEntity);
                    saga.setBibEntity(sagaBibEntity);
                    sagaVersionRepository.save(saga);
                }
            }
        }

        bibRepository.deleteById(id);
    }
}
