package com.se.riddaradb.place;

import com.se.riddaradb.character.PersonEntity;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlaceMapper {

    public PlaceDto mapToDto(PlaceEntity placeEntity){
        PlaceDto placeDto = new PlaceDto(placeEntity.getId(), placeEntity.getName(), placeEntity.getType());

        placeDto.setPersonIds(placeEntity.getPersonEntity()
                .stream()
                .map(PersonEntity::getId)
                .collect(Collectors.toSet()));

        placeDto.setSagaVersionIds(placeEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return placeDto;
    }

    public PlaceEntity mapFromDto(PlaceDto placeDto){
        return new PlaceEntity(placeDto.getId(), placeDto.getName(), placeDto.getType());
    }
}
