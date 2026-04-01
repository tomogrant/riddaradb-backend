package com.se.riddaradb.services;

import com.se.riddaradb.dtos.PlaceDto;
import com.se.riddaradb.entities.PersonEntity;
import com.se.riddaradb.entities.PlaceEntity;
import com.se.riddaradb.entities.SagaEntity;
import com.se.riddaradb.mappers.PlaceMapper;
import com.se.riddaradb.repositories.PersonRepository;
import com.se.riddaradb.repositories.PlaceRepository;
import com.se.riddaradb.repositories.SagaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class PlaceService {

    final PlaceRepository placeRepository;
    final SagaRepository sagaRepository;
    final PersonRepository personRepository;
    final PlaceMapper placeMapper;

    public PlaceService(PlaceRepository placeRepository, SagaRepository sagaRepository, PersonRepository personRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.sagaRepository = sagaRepository;
        this.personRepository = personRepository;
        this.placeMapper = placeMapper;
    }

    public Collection<PlaceDto> getPlaceEntries(){
        return placeRepository.findAll()
                .stream()
                .map(placeMapper::mapToDto)
                .toList();
    }

    public PlaceDto getPlaceEntryById(int id){
        if (placeRepository.findById(id).isPresent()){
            return placeMapper.mapToDto(placeRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public PlaceDto savePlaceEntry(PlaceDto placeDto){
        PlaceEntity placeEntity = placeMapper.mapFromDto(placeDto);
        placeEntity.setSagaEntity(new HashSet<>(sagaRepository.findAllById(placeDto.getSagaIds())));
        placeEntity.setPersonEntity(new HashSet<>(personRepository.findAllById(placeDto.getPersonIds())));
        return placeMapper.mapToDto(placeRepository.save(placeEntity));
    }

    public void deletePlaceEntryById(int id) {

        removePlaceFromSagaEntries(id);
        removePlaceFromPersonEntries(id);

        placeRepository.deleteById(id);
    }

    private void removePlaceFromSagaEntries(int id){
        Set<SagaEntity> sagaEntities = new HashSet<SagaEntity>(sagaRepository.findAll());

        //REMOVE PLACE FROM SAGA
        for (SagaEntity saga : sagaEntities) {
            Set<PlaceEntity> sagaPlaceEntity = new HashSet<PlaceEntity>(saga.getPlaceEntity());
            for (PlaceEntity placeEntity : sagaPlaceEntity) {
                if (placeEntity.getId() == id) {
                    sagaPlaceEntity.remove(placeEntity);
                    saga.setPlaceEntity(sagaPlaceEntity);
                    sagaRepository.save(saga);
                }
            }
        }
    }

    private void removePlaceFromPersonEntries(int id){
        Set<PersonEntity> personEntities = new HashSet<PersonEntity>(personRepository.findAll());

        //REMOVE PLACE FROM PERSON
        for (PersonEntity person : personEntities) {
            Set<PlaceEntity> personPlaceEntity = new HashSet<PlaceEntity>(person.getPlaceEntity());
            for (PlaceEntity placeEntity : personPlaceEntity) {
                if (placeEntity.getId() == id) {
                    personPlaceEntity.remove(placeEntity);
                    person.setPlaceEntity(personPlaceEntity);
                    personRepository.save(person);
                }
            }
        }
    }
}


