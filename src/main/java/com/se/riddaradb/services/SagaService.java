package com.se.riddaradb.services;

import com.se.riddaradb.dtos.SagaDto;
import com.se.riddaradb.entities.*;
import com.se.riddaradb.mappers.SagaMapper;
import com.se.riddaradb.repositories.*;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class SagaService {

    final SagaRepository sagaRepository;
    final BibRepository bibRepository;
    final FolkloreRepository folkloreRepository;
    final PersonRepository personRepository;
    final PlaceRepository placeRepository;
    final ObjectRepository objectRepository;
    final MsRepository msRepository;
    final SagaMapper sagaMapper;

    public SagaService(SagaRepository sagaRepository,
                       BibRepository bibRepository,
                       FolkloreRepository folkloreRepository,
                       PersonRepository personRepository,
                       PlaceRepository placeRepository,
                       ObjectRepository objectRepository,
                       MsRepository msRepository,
                       SagaMapper sagaMapper) {

        this.sagaRepository = sagaRepository;
        this.bibRepository = bibRepository;
        this.folkloreRepository = folkloreRepository;
        this.personRepository = personRepository;
        this.placeRepository = placeRepository;
        this.objectRepository = objectRepository;
        this.msRepository = msRepository;
        this.sagaMapper = sagaMapper;
    }

    public Collection<SagaDto> getSagas(){
        return sagaRepository.findAll()
                .stream()
                .map(sagaMapper::mapToDto)
                .toList();
    }

    public SagaDto getSagaById(int id){
        if (sagaRepository.findById(id).isPresent()){
            return sagaMapper.mapToDto(sagaRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public SagaDto saveSaga(SagaDto sagaDto){
        SagaEntity sagaEntity = sagaMapper.mapFromDto(sagaDto);

        sagaEntity.setBibEntity(new HashSet<>(bibRepository.findAllById(sagaDto.getBibIds())));
        sagaEntity.setFolkloreEntity(new HashSet<>(folkloreRepository.findAllById(sagaDto.getFolkloreIds())));
        sagaEntity.setPersonEntity(new HashSet<>(personRepository.findAllById(sagaDto.getPersonIds())));
        sagaEntity.setPlaceEntity(new HashSet<>(placeRepository.findAllById(sagaDto.getPlaceIds())));
        sagaEntity.setObjectEntity(new HashSet<>(objectRepository.findAllById(sagaDto.getObjectIds())));
        sagaEntity.setMsEntity(new HashSet<>(msRepository.findAllById(sagaDto.getMsIds())));

        return sagaMapper.mapToDto(sagaRepository.save(sagaEntity));
    }

    public void deleteSagaById(int id) {

        if (sagaRepository.existsById(id)){
            removeSagaFromBibEntries(id);
            removeSagaFromFolkloreEntries(id);
            removeSagaFromMsEntries(id);
            removeSagaFromObjectEntries(id);
            removeSagaFromPersonEntries(id);
            removeSagaFromPlaceEntries(id);

            sagaRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }

    private void removeSagaFromBibEntries(int id){
        Set<BibEntity> bibEntitiesSet = new HashSet<>(bibRepository.findAll());
        for(BibEntity bibEntity : bibEntitiesSet){
            Set<SagaEntity> bibSagaEntitiesSet = new HashSet<>(bibEntity.getSagaEntity());
            for(SagaEntity sagaEntity : bibSagaEntitiesSet){
                if (sagaEntity.getId() == id) {
                    bibSagaEntitiesSet.remove(sagaEntity);
                    bibEntity.setSagaEntity(bibSagaEntitiesSet);
                    bibRepository.save(bibEntity);
                }
            }
        }
    }

    private void removeSagaFromFolkloreEntries(int id){
        Set<FolkloreEntity> folkloreEntitiesSet = new HashSet<>(folkloreRepository.findAll());
        for(FolkloreEntity folkloreEntity : folkloreEntitiesSet){
            Set<SagaEntity> folkloreSagaEntitiesSet = new HashSet<>(folkloreEntity.getSagaEntity());
            for(SagaEntity sagaEntity : folkloreSagaEntitiesSet){
                if (sagaEntity.getId() == id) {
                    folkloreSagaEntitiesSet.remove(sagaEntity);
                    folkloreEntity.setSagaEntity(folkloreSagaEntitiesSet);
                    folkloreRepository.save(folkloreEntity);
                }
            }
        }
    }

    private void removeSagaFromMsEntries(int id){
        Set<MsEntity> msEntitiesSet = new HashSet<>(msRepository.findAll());
        for(MsEntity msEntity : msEntitiesSet){
            Set<SagaEntity> msSagaEntitiesSet = new HashSet<>(msEntity.getSagaEntity());
            for(SagaEntity sagaEntity : msSagaEntitiesSet){
                if (sagaEntity.getId() == id) {
                    msSagaEntitiesSet.remove(sagaEntity);
                    msEntity.setSagaEntity(msSagaEntitiesSet);
                    msRepository.save(msEntity);
                }
            }
        }
    }

    private void removeSagaFromObjectEntries(int id){
        Set<ObjectEntity> objectEntitiesSet = new HashSet<>(objectRepository.findAll());
        for(ObjectEntity objectEntity : objectEntitiesSet){
            Set<SagaEntity> objectSagaEntitiesSet = new HashSet<>(objectEntity.getSagaEntity());
            for(SagaEntity sagaEntity : objectSagaEntitiesSet){
                if (sagaEntity.getId() == id) {
                    objectSagaEntitiesSet.remove(sagaEntity);
                    objectEntity.setSagaEntity(objectSagaEntitiesSet);
                    objectRepository.save(objectEntity);
                }
            }
        }
    }

    private void removeSagaFromPersonEntries(int id){
        Set<PersonEntity> personEntitiesSet = new HashSet<>(personRepository.findAll());
        for(PersonEntity personEntity : personEntitiesSet){
            Set<SagaEntity> personSagaEntitiesSet = new HashSet<>(personEntity.getSagaEntity());
            for(SagaEntity sagaEntity : personSagaEntitiesSet){
                if (sagaEntity.getId() == id) {
                    personSagaEntitiesSet.remove(sagaEntity);
                    personEntity.setSagaEntity(personSagaEntitiesSet);
                    personRepository.save(personEntity);
                }
            }
        }
    }

    private void removeSagaFromPlaceEntries(int id){
        Set<PlaceEntity> placeEntitiesSet = new HashSet<>(placeRepository.findAll());
        for(PlaceEntity placeEntity : placeEntitiesSet){
            Set<SagaEntity> placeSagaEntitiesSet = new HashSet<>(placeEntity.getSagaEntity());
            for(SagaEntity sagaEntity : placeSagaEntitiesSet){
                if (sagaEntity.getId() == id) {
                    placeSagaEntitiesSet.remove(sagaEntity);
                    placeEntity.setSagaEntity(placeSagaEntitiesSet);
                    placeRepository.save(placeEntity);
                }
            }
        }
    }
}
