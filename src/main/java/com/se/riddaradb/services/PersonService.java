package com.se.riddaradb.services;

import com.se.riddaradb.dtos.PersonDto;
import com.se.riddaradb.entities.PersonEntity;
import com.se.riddaradb.entities.PlaceEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.mappers.PersonMapper;
import com.se.riddaradb.repositories.PersonRepository;
import com.se.riddaradb.repositories.PlaceRepository;
import com.se.riddaradb.repositories.SagaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class PersonService {

    final PlaceRepository placeRepository;
    final SagaVersionRepository sagaVersionRepository;
    final PersonRepository personRepository;
    final PersonMapper personMapper;

    public PersonService(PlaceRepository placeRepository, SagaVersionRepository sagaVersionRepository, PersonRepository personRepository, PersonMapper personMapper) {
        this.placeRepository = placeRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Collection<PersonDto> getPersonEntries(){
        return personRepository.findAll()
                .stream()
                .map(personMapper::mapToDto)
                .toList();
    }

    public PersonDto getPersonEntryById(int id){
        if (personRepository.findById(id).isPresent()){
            return personMapper.mapToDto(personRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public PersonDto savePersonEntry(PersonDto personDto){
        PersonEntity personEntity = personMapper.mapFromDto(personDto);
        personEntity.setSagaVersionEntity(new HashSet<>(sagaVersionRepository.findAllById(personDto.getSagaVersionIds())));
        personEntity.setPlaceEntity(new HashSet<>(placeRepository.findAllById(personDto.getPlaceIds())));
        return personMapper.mapToDto(personRepository.save(personEntity));
    }

    public void deletePersonEntryById(int id) {

        removePersonFromSagaEntries(id);
        removePersonFromPlaceEntries(id);

        placeRepository.deleteById(id);
    }

    private void removePersonFromSagaEntries(int id){
        Set<SagaVersionEntity> sagaEntities = new HashSet<SagaVersionEntity>(sagaVersionRepository.findAll());

        //REMOVE PLACE FROM SAGA
        for (SagaVersionEntity saga : sagaEntities) {
            Set<PersonEntity> sagaPersonEntity = new HashSet<PersonEntity>(saga.getPersonEntity());
            for (PersonEntity personEntity : sagaPersonEntity) {
                if (personEntity.getId() == id) {
                    sagaPersonEntity.remove(personEntity);
                    saga.setPersonEntity(sagaPersonEntity);
                    sagaVersionRepository.save(saga);
                }
            }
        }
    }

    private void removePersonFromPlaceEntries(int id){
        //REMOVE PLACE FROM PERSON
        Set<PlaceEntity> placeEntities = new HashSet<PlaceEntity>(placeRepository.findAll());

        //REMOVE PLACE FROM SAGA
        for (PlaceEntity place : placeEntities) {
            Set<PersonEntity> placePersonEntity = new HashSet<PersonEntity>(place.getPersonEntity());
            for (PersonEntity personEntity : placePersonEntity) {
                if (personEntity.getId() == id) {
                    placePersonEntity.remove(personEntity);
                    place.setPersonEntity(placePersonEntity);
                    placeRepository.save(place);
                }
            }
        }
    }
}


