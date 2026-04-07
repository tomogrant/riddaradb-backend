package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.ObjectDto;
import com.se.riddaradb.dtos.SagaDto;
import com.se.riddaradb.dtos.SagaVersionDto;
import com.se.riddaradb.entities.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SagaMapper {

    public SagaDto mapToDto(SagaEntity sagaEntity){
        SagaDto sagaDto = new SagaDto(sagaEntity.getId(), sagaEntity.getTitle(), sagaEntity.getDescription());

        sagaDto.setSagaVersionIds(sagaEntity.getSagaVersionEntities()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return sagaDto;
    }

    public SagaEntity mapFromDto(SagaDto sagaDto){
        return new SagaEntity(sagaDto.getId(), sagaDto.getTitle(), sagaDto.getDescription());
    }
}
