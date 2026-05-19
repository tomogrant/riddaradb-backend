package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.MotifDto;
import com.se.riddaradb.entities.MotifEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import com.se.riddaradb.repositories.MotifRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class MotifMapper {

    public MotifDto mapToDto(MotifEntity motifEntity){
        MotifDto motifDto = new MotifDto(motifEntity.getId(), motifEntity.getMotifCode(), motifEntity.getMotifName(), motifEntity.getDescription(), !motifEntity.getChildren().isEmpty());

        if (motifEntity.getParent() != null){
            motifDto.setParentId(motifEntity.getParent().getId());
        }

        motifDto.setSagaVersionIds(motifEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return motifDto;
    }

    public MotifEntity mapFromDto(MotifDto motifDto){

        return new MotifEntity(motifDto.getId(), motifDto.getMotifCode(), motifDto.getMotifName(), motifDto.getDescription());

    }
}
