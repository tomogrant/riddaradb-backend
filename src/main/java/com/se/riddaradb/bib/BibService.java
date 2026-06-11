package com.se.riddaradb.bib;

import com.se.riddaradb.saga.SagaEntity;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import com.se.riddaradb.saga.SagaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class BibService {

    final BibRepository bibRepository;
    final SagaRepository sagaRepository;
    final BibMapper bibMapper;

    public BibService(BibRepository bibRepository, SagaRepository sagaRepository, BibMapper bibMapper) {
        this.bibRepository = bibRepository;
        this.sagaRepository = sagaRepository;
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
        if (bibDto.getId() != null){
            if (bibRepository.existsById(bibDto.getId())){
                removeBibFromSagaVersions(bibDto.getId());
            }
        }

        BibEntity bibEntity;

        //Creates bib entity
        if (bibDto.getId() == null){
            bibEntity = bibMapper.mapFromDto(bibDto);
        }
        else{
            bibEntity = bibRepository.findById(bibDto.getId()).orElseThrow();
        }

        //Add bib entry to sagas
        for (int id : bibDto.getSagaIds()){
            sagaRepository.findById(id).ifPresent(saga -> saga.addBib(bibEntity));
        }

        return bibMapper.mapToDto(bibRepository.save(bibEntity));
    }

    public void deleteBibEntryById(int id){

        removeBibFromSagaVersions(id);
        bibRepository.deleteById(id);
    }

    private void removeBibFromSagaVersions(int id){
        for (SagaEntity saga : sagaRepository.findAll()){
            saga.getBibEntity().removeIf(bib -> bib.getId() == id);
        }
    }
}
