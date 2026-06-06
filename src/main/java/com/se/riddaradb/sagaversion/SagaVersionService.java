package com.se.riddaradb.sagaversion;

import com.se.riddaradb.bib.BibRepository;
import com.se.riddaradb.character.PersonRepository;
import com.se.riddaradb.motif.MotifRepository;
import com.se.riddaradb.ms.MsRepository;
import com.se.riddaradb.object.ObjectRepository;
import com.se.riddaradb.place.PlaceRepository;
import com.se.riddaradb.saga.SagaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SagaVersionService {

    final SagaRepository sagaRepository;
    final SagaVersionRepository sagaVersionRepository;
    final BibRepository bibRepository;
    final MotifRepository motifRepository;
    final SagaVersionMotifRepository sagaVersionMotifRepository;
    final PersonRepository personRepository;
    final PlaceRepository placeRepository;
    final ObjectRepository objectRepository;
    final MsRepository msRepository;
    final SagaVersionMapper sagaVersionMapper;

    public SagaVersionService(SagaRepository sagaRepository,
                              SagaVersionRepository sagaVersionRepository,
                              BibRepository bibRepository,
                              MotifRepository motifRepository,
                              SagaVersionMotifRepository sagaVersionMotifRepository,
                              PersonRepository personRepository,
                              PlaceRepository placeRepository,
                              ObjectRepository objectRepository,
                              MsRepository msRepository,
                              SagaVersionMapper sagaVersionMapper) {

        this.sagaRepository = sagaRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.bibRepository = bibRepository;
        this.motifRepository = motifRepository;
        this.sagaVersionMotifRepository = sagaVersionMotifRepository;
        this.personRepository = personRepository;
        this.placeRepository = placeRepository;
        this.objectRepository = objectRepository;
        this.msRepository = msRepository;
        this.sagaVersionMapper = sagaVersionMapper;
    }

    public Set<SagaVersionResponseDto> getSagaVersions(){
        return sagaVersionRepository.findAll()
                .stream()
                .map(sagaVersionMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    public Set<SagaVersionTitleDto> getSagaVersionTitles(){
        return sagaVersionRepository.findAll()
                .stream()
                .map(sagaVersionMapper::mapToTitleDto)
                .collect(Collectors.toSet());
    }

    public SagaVersionResponseDto getSagaVersionById(int id){
        if (sagaVersionRepository.findById(id).isPresent()){
            return sagaVersionMapper.mapToDto(sagaVersionRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public SagaVersionResponseDto saveSagaVersion(SagaVersionRequestDto sagaVersionRequestDto){
        SagaVersionEntity sagaVersionEntity = sagaVersionMapper.mapFromDto(sagaVersionRequestDto);

        if (sagaRepository.findById(sagaVersionRequestDto.getSagaId()).isPresent()){
            sagaVersionEntity.setSagaEntity(sagaRepository.findById(sagaVersionRequestDto.getSagaId()).get());
        }

        sagaVersionEntity.setSagaVersionMotifEntities(sagaVersionMotifRepository.findBySagaVersionEntityId(sagaVersionEntity.getId()));

        sagaVersionEntity.setPersonEntity(new HashSet<>(personRepository.findAllById(sagaVersionRequestDto.getPersonIds())));
        sagaVersionEntity.setPlaceEntity(new HashSet<>(placeRepository.findAllById(sagaVersionRequestDto.getPlaceIds())));
        sagaVersionEntity.setObjectEntity(new HashSet<>(objectRepository.findAllById(sagaVersionRequestDto.getObjectIds())));
        sagaVersionEntity.setMsEntity(new HashSet<>(msRepository.findAllById(sagaVersionRequestDto.getMsIds())));

        return sagaVersionMapper.mapToDto(sagaVersionRepository.save(sagaVersionEntity));
    }

    public void deleteSagaVersionById(int id) {

        if (sagaVersionRepository.existsById(id)) {
            sagaVersionRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }



}
