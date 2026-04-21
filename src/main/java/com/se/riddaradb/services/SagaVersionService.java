package com.se.riddaradb.services;

import com.se.riddaradb.dtos.SagaVersionRequestDto;
import com.se.riddaradb.dtos.SagaVersionResponseDto;
import com.se.riddaradb.entities.*;
import com.se.riddaradb.mappers.SagaVersionMapper;
import com.se.riddaradb.repositories.*;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SagaVersionService {

    final SagaRepository sagaRepository;
    final SagaVersionRepository sagaVersionRepository;
    final BibRepository bibRepository;
    final FolkloreRepository folkloreRepository;
    final PersonRepository personRepository;
    final PlaceRepository placeRepository;
    final ObjectRepository objectRepository;
    final MsRepository msRepository;
    final SagaVersionMapper sagaVersionMapper;

    public SagaVersionService(SagaRepository sagaRepository,
                              SagaVersionRepository sagaVersionRepository,
                              BibRepository bibRepository,
                              FolkloreRepository folkloreRepository,
                              PersonRepository personRepository,
                              PlaceRepository placeRepository,
                              ObjectRepository objectRepository,
                              MsRepository msRepository,
                              SagaVersionMapper sagaVersionMapper) {

        this.sagaRepository = sagaRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.bibRepository = bibRepository;
        this.folkloreRepository = folkloreRepository;
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

        sagaVersionEntity.setBibEntity(new HashSet<>(bibRepository.findAllById(sagaVersionRequestDto.getBibIds())));
        sagaVersionEntity.setFolkloreEntity(new HashSet<>(folkloreRepository.findAllById(sagaVersionRequestDto.getFolkloreIds())));
        sagaVersionEntity.setPersonEntity(new HashSet<>(personRepository.findAllById(sagaVersionRequestDto.getPersonIds())));
        sagaVersionEntity.setPlaceEntity(new HashSet<>(placeRepository.findAllById(sagaVersionRequestDto.getPlaceIds())));
        sagaVersionEntity.setObjectEntity(new HashSet<>(objectRepository.findAllById(sagaVersionRequestDto.getObjectIds())));
        sagaVersionEntity.setMsEntity(new HashSet<>(msRepository.findAllById(sagaVersionRequestDto.getMsIds())));

        return sagaVersionMapper.mapToDto(sagaVersionRepository.save(sagaVersionEntity));
    }

    public void deleteSagaVersionById(int id) {

        if (sagaVersionRepository.existsById(id)){
            removeSagaVersionFromBibEntries(id);
            removeSagaVersionFromFolkloreEntries(id);
            removeSagaVersionFromMsEntries(id);
            removeSagaVersionFromObjectEntries(id);
            removeSagaVersionFromPersonEntries(id);
            removeSagaVersionFromPlaceEntries(id);

            sagaVersionRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }

    private void removeSagaVersionFromBibEntries(int id){
        Set<BibEntity> bibEntitiesSet = new HashSet<>(bibRepository.findAll());
        for(BibEntity bibEntity : bibEntitiesSet){
            Set<SagaVersionEntity> bibSagaEntitiesSet = new HashSet<>(bibEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : bibSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    bibSagaEntitiesSet.remove(sagaVersionEntity);
                    bibEntity.setSagaVersionEntity(bibSagaEntitiesSet);
                    bibRepository.save(bibEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromFolkloreEntries(int id){
        Set<FolkloreEntity> folkloreEntitiesSet = new HashSet<>(folkloreRepository.findAll());
        for(FolkloreEntity folkloreEntity : folkloreEntitiesSet){
            Set<SagaVersionEntity> folkloreSagaEntitiesSet = new HashSet<>(folkloreEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : folkloreSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    folkloreSagaEntitiesSet.remove(sagaVersionEntity);
                    folkloreEntity.setSagaVersionEntity(folkloreSagaEntitiesSet);
                    folkloreRepository.save(folkloreEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromMsEntries(int id){
        Set<MsEntity> msEntitiesSet = new HashSet<>(msRepository.findAll());
        for(MsEntity msEntity : msEntitiesSet){
            Set<SagaVersionEntity> msSagaEntitiesSet = new HashSet<>(msEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : msSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    msSagaEntitiesSet.remove(sagaVersionEntity);
                    msEntity.setSagaVersionEntity(msSagaEntitiesSet);
                    msRepository.save(msEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromObjectEntries(int id){
        Set<ObjectEntity> objectEntitiesSet = new HashSet<>(objectRepository.findAll());
        for(ObjectEntity objectEntity : objectEntitiesSet){
            Set<SagaVersionEntity> objectSagaEntitiesSet = new HashSet<>(objectEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : objectSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    objectSagaEntitiesSet.remove(sagaVersionEntity);
                    objectEntity.setSagaVersionEntity(objectSagaEntitiesSet);
                    objectRepository.save(objectEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromPersonEntries(int id){
        Set<PersonEntity> personEntitiesSet = new HashSet<>(personRepository.findAll());
        for(PersonEntity personEntity : personEntitiesSet){
            Set<SagaVersionEntity> personSagaEntitiesSet = new HashSet<>(personEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : personSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    personSagaEntitiesSet.remove(sagaVersionEntity);
                    personEntity.setSagaVersionEntity(personSagaEntitiesSet);
                    personRepository.save(personEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromPlaceEntries(int id){
        Set<PlaceEntity> placeEntitiesSet = new HashSet<>(placeRepository.findAll());
        for(PlaceEntity placeEntity : placeEntitiesSet){
            Set<SagaVersionEntity> placeSagaEntitiesSet = new HashSet<>(placeEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : placeSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    placeSagaEntitiesSet.remove(sagaVersionEntity);
                    placeEntity.setSagaVersionEntity(placeSagaEntitiesSet);
                    placeRepository.save(placeEntity);
                }
            }
        }
    }
}
