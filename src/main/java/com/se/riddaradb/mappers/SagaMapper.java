package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.SagaRequestDto;
import com.se.riddaradb.dtos.SagaResponseDto;
import com.se.riddaradb.entities.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SagaMapper {

    final SagaVersionMapper sagaVersionMapper;

    public SagaMapper(SagaVersionMapper sagaVersionMapper){
        this.sagaVersionMapper = sagaVersionMapper;
    }

    public SagaResponseDto mapToDto(SagaEntity sagaEntity){
        return new SagaResponseDto(sagaEntity.getId(), sagaEntity.getTitle(), sagaEntity.getDescription(), sagaEntity.getSagaVersionEntities()
                .stream()
                .map(sagaVersionMapper::mapToDto)
                .collect(Collectors.toSet()));
    }

    public SagaEntity mapFromDto(SagaRequestDto sagaRequestDto){
        return new SagaEntity(sagaRequestDto.getId(), sagaRequestDto.getTitle(), sagaRequestDto.getDescription());
    }
}
