package com.se.riddaradb.character;

import com.se.riddaradb.place.PlaceEntity;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonMapper {

    public PersonDto mapToDto(PersonEntity personEntity){
        PersonDto personDto = new PersonDto(personEntity.getId(), personEntity.getName(), personEntity.getSpecies(), personEntity.getRealWorldRef());

        personDto.setPlaceIds(personEntity.getPlaceEntity()
                .stream()
                .map(PlaceEntity::getId)
                .collect(Collectors.toSet()));

        personDto.setSagaVersionIds(personEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return personDto;
    }

    public PersonEntity mapFromDto(PersonDto personDto){
        return new PersonEntity(personDto.getId(), personDto.getName(), personDto.getSpecies(), personDto.getRealWorldRef());
    }
}
