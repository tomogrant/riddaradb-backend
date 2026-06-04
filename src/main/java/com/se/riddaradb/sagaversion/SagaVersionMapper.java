package com.se.riddaradb.sagaversion;

import com.se.riddaradb.bib.BibMapper;
import com.se.riddaradb.character.PersonEntity;
import com.se.riddaradb.motif.MotifSagaVersionDto;
import com.se.riddaradb.ms.MsEntity;
import com.se.riddaradb.object.ObjectEntity;
import com.se.riddaradb.place.PlaceEntity;
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
        SagaVersionResponseDto sagaVersionResponseDto = new SagaVersionResponseDto(sagaVersionEntity.getId(), sagaVersionEntity.getTitle(), sagaVersionEntity.getDescription(), sagaVersionEntity.getDate());

        if (sagaVersionEntity.getSagaEntity() != null)
            sagaVersionResponseDto.setSagaId(sagaVersionEntity.getSagaEntity().getId());

        sagaVersionResponseDto.setBibDto(sagaVersionEntity.getBibEntity()
                .stream()
                .map(bibMapper::mapToDto)
                .collect(Collectors.toSet()));

        for (SagaVersionMotifEntity sagaVersionMotifEntity : sagaVersionEntity.getSagaVersionMotifEntities()){
            sagaVersionResponseDto.getSagaMotifs().add(new SagaVersionMotifDto
                    (sagaVersionMotifEntity.getMotifEntity().getId(),
                            sagaVersionMotifEntity.getMotifEntity().getMotifCode(),
                            sagaVersionMotifEntity.getMotifEntity().getMotifName(),
                            sagaVersionMotifEntity.getPageChapterNumber()));
        }

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
        return new SagaVersionEntity(sagaVersionRequestDto.getId(), sagaVersionRequestDto.getTitle(), sagaVersionRequestDto.getDescription(), sagaVersionRequestDto.getDate());
    }

    public SagaVersionTitleDto mapToTitleDto(SagaVersionEntity sagaVersionEntity){
        return new SagaVersionTitleDto(sagaVersionEntity.getId(), sagaVersionEntity.getSagaEntity().getId(), sagaVersionEntity.getTitle());
    }
}
