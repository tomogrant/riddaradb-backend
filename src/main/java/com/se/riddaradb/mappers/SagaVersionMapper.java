package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.SagaVersionRequestDto;
import com.se.riddaradb.dtos.SagaVersionResponseDto;
import com.se.riddaradb.entities.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class SagaVersionMapper {

    final BibMapper bibMapper;

    public SagaVersionMapper(BibMapper bibMapper){
        this.bibMapper = bibMapper;
    }

    //Consumes DB entity and produces response for frontend
    public SagaVersionResponseDto mapToDto(SagaVersionEntity sagaVersionEntity){
        SagaVersionResponseDto sagaVersionResponseDto = new SagaVersionResponseDto(sagaVersionEntity.getId(), sagaVersionEntity.getTitle(), sagaVersionEntity.getDescription(), sagaVersionEntity.getDate(), sagaVersionEntity.getIsTranslated());

        if (sagaVersionEntity.getSagaEntity() != null)
            sagaVersionResponseDto.setSagaId(sagaVersionEntity.getSagaEntity().getId());

        sagaVersionResponseDto.setBibDto(sagaVersionEntity.getBibEntity()
                .stream()
                .map(bibMapper::mapToDto)
                .collect(Collectors.toSet()));

        sagaVersionResponseDto.setFolkloreIds(sagaVersionEntity.getFolkloreEntity()
                .stream()
                .map(FolkloreEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionResponseDto.setPersonIds(sagaVersionEntity.getPersonEntity()
                .stream()
                .map(PersonEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionResponseDto.setPlaceIds(sagaVersionEntity.getPlaceEntity()
                .stream()
                .map(PlaceEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionResponseDto.setObjectIds(sagaVersionEntity.getObjectEntity()
                .stream()
                .map(ObjectEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionResponseDto.setMsIds(sagaVersionEntity.getMsEntity()
                .stream()
                .map(MsEntity::getId)
                .collect(Collectors.toSet()));

        return sagaVersionResponseDto;
    }

    //Consumes request from frontend and produces DB entity for persistence
    public SagaVersionEntity mapFromDto(SagaVersionRequestDto sagaVersionRequestDto){
        return new SagaVersionEntity(sagaVersionRequestDto.getId(), sagaVersionRequestDto.getTitle(), sagaVersionRequestDto.getDescription(), sagaVersionRequestDto.getDate(), sagaVersionRequestDto.getIsTranslated());
    }
}
