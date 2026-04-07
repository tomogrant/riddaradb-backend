package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.SagaVersionDto;
import com.se.riddaradb.entities.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class SagaVersionMapper {

    public SagaVersionDto mapToDto(SagaVersionEntity sagaVersionEntity){
        SagaVersionDto sagaVersionDto = new SagaVersionDto(sagaVersionEntity.getId(), sagaVersionEntity.getTitle(), sagaVersionEntity.getDescription(), sagaVersionEntity.getDate(), sagaVersionEntity.getIsTranslated());

        sagaVersionDto.setSagaId(sagaVersionEntity.getSagaEntity().getId());

        sagaVersionDto.setBibIds(sagaVersionEntity.getBibEntity()
                .stream()
                .map(BibEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionDto.setFolkloreIds(sagaVersionEntity.getFolkloreEntity()
                .stream()
                .map(FolkloreEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionDto.setPersonIds(sagaVersionEntity.getPersonEntity()
                .stream()
                .map(PersonEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionDto.setPlaceIds(sagaVersionEntity.getPlaceEntity()
                .stream()
                .map(PlaceEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionDto.setObjectIds(sagaVersionEntity.getObjectEntity()
                .stream()
                .map(ObjectEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionDto.setMsIds(sagaVersionEntity.getMsEntity()
                .stream()
                .map(MsEntity::getId)
                .collect(Collectors.toSet()));

        return sagaVersionDto;
    }

    public SagaVersionEntity mapFromDto(SagaVersionDto sagaVersionDto){
        return new SagaVersionEntity(sagaVersionDto.getId(), sagaVersionDto.getTitle(), sagaVersionDto.getDescription(), sagaVersionDto.getDate(), sagaVersionDto.getIsTranslated());
    }
}
