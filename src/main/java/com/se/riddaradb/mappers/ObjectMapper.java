package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.ObjectDto;
import com.se.riddaradb.entities.ObjectEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ObjectMapper {

    public ObjectDto mapToDto(ObjectEntity objectEntity){
        ObjectDto objectDto = new ObjectDto(objectEntity.getId(), objectEntity.getName(), objectEntity.getDescription(), objectEntity.getType());

        objectDto.setSagaVersionIds(objectEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return objectDto;
    }

    public ObjectEntity mapFromDto(ObjectDto objectDto){
        return new ObjectEntity(objectDto.getId(), objectDto.getName(), objectDto.getDescription(), objectDto.getType());
    }
}
