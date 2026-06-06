package com.se.riddaradb.saga;

import com.se.riddaradb.bib.BibMapper;
import com.se.riddaradb.sagaversion.SagaVersionMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SagaMapper {

    final SagaVersionMapper sagaVersionMapper;
    final BibMapper bibMapper;

    public SagaMapper(SagaVersionMapper sagaVersionMapper, BibMapper bibMapper){
        this.sagaVersionMapper = sagaVersionMapper;
        this.bibMapper = bibMapper;
    }

    public SagaResponseDto mapToResponseDto(SagaEntity sagaEntity){
        SagaResponseDto sagaResponseDto = new SagaResponseDto(sagaEntity.getId(), sagaEntity.getTitle(), sagaEntity.getDescription(), sagaEntity.getTranslated());

        sagaResponseDto.setSagaVersions(sagaEntity.getSagaVersionEntities()
                .stream()
                .map(sagaVersionMapper::mapToDto)
                .collect(Collectors.toSet()));

        sagaResponseDto.setBibDto(sagaEntity.getBibEntity()
                .stream()
                .map(bibMapper::mapToDto)
                .collect(Collectors.toSet()));

        return sagaResponseDto;
    }

    public SagaEntity mapToEntity(SagaRequestDto sagaRequestDto){
        return new SagaEntity(sagaRequestDto.getId(), sagaRequestDto.getTitle(), sagaRequestDto.getDescription(), sagaRequestDto.getTranslated());
    }

    public SagaTitleDto mapToSagaTitle(SagaEntity sagaEntity){
        return new SagaTitleDto(sagaEntity.getId(), sagaEntity.getTitle());
    }
}
