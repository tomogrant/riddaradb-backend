package com.se.riddaradb.services;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.BibMapper;
import com.se.riddaradb.entities.BibEntity;
import com.se.riddaradb.repositories.BibRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
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

        //This is the most beautiful code I've ever written.
        //Removes bib entity from sagas
        if (bibRepository.existsById(bibDto.getId())){
            removeBibFromSagaVersions(bibDto.getId());
        }

        //Creates bib entity
        BibEntity bibEntity = bibMapper.mapFromDto(bibDto);

        //Add bib entry to sagas
        for (int id : bibDto.getSagaVersionIds()){
            sagaVersionRepository.findById(id).ifPresent(sagaVersion -> sagaVersion.addBib(bibEntity));
        }

        return bibMapper.mapToDto(bibRepository.save(bibEntity));
    }

    public void deleteBibEntryById(int id){

        removeBibFromSagaVersions(id);
        bibRepository.deleteById(id);
    }

    private void removeBibFromSagaVersions(int id){
        for (SagaVersionEntity saga : sagaVersionRepository.findAll()){
            saga.getBibEntity().removeIf(bib -> bib.getId() == id);
        }
    }
}
