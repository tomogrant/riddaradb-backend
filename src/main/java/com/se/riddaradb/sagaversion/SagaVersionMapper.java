package com.se.riddaradb.sagaversion;

import com.se.riddaradb.character.CharacterEntity;
import com.se.riddaradb.location.LocationEntity;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class SagaVersionMapper {

    //Consumes DB entity and produces response for frontend
    public SagaVersionResponseDto mapToDto(SagaVersionEntity sagaVersionEntity){
        SagaVersionResponseDto sagaVersionResponseDto = new SagaVersionResponseDto(sagaVersionEntity.getId(), sagaVersionEntity.getTitle(), sagaVersionEntity.getDescription(), sagaVersionEntity.getDate());

        if (sagaVersionEntity.getSagaEntity() != null)
            sagaVersionResponseDto.setSagaId(sagaVersionEntity.getSagaEntity().getId());

        for (SagaVersionMotifEntity sagaVersionMotifEntity : sagaVersionEntity.getSagaVersionMotifEntities()){
            sagaVersionResponseDto.getSagaMotifs().add(new SagaVersionMotifDto
                    (sagaVersionMotifEntity.getMotifEntity().getId(),
                            sagaVersionMotifEntity.getMotifEntity().getMotifCode(),
                            sagaVersionMotifEntity.getMotifEntity().getMotifName(),
                            sagaVersionMotifEntity.getPageChapterNumber()));
        }

        sagaVersionResponseDto.setCharacterIds(sagaVersionEntity.getCharacterEntity()
                .stream()
                .map(CharacterEntity::getId)
                .collect(Collectors.toSet()));

        sagaVersionResponseDto.setLocationIds(sagaVersionEntity.getLocationEntity()
                .stream()
                .map(LocationEntity::getId)
                .collect(Collectors.toSet()));

        return sagaVersionResponseDto;
    }

    public SagaVersionTitleDto mapToTitleDto(SagaVersionEntity sagaVersionEntity){
        return new SagaVersionTitleDto(sagaVersionEntity.getId(), sagaVersionEntity.getSagaEntity().getId(), sagaVersionEntity.getTitle());
    }
}
