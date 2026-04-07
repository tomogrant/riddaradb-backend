package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.FolkloreDto;
import com.se.riddaradb.entities.FolkloreEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FolkloreMapper {

    public FolkloreDto mapToDto(FolkloreEntity folkloreEntity){
        FolkloreDto folkloreDto = new FolkloreDto(folkloreEntity.getId(), folkloreEntity.getName(), folkloreEntity.getDescription(), folkloreEntity.getMotifCode());

        folkloreDto.setSagaVersionIds(folkloreEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return folkloreDto;
    }

    public FolkloreEntity mapFromDto(FolkloreDto folkloreDto){
        return new FolkloreEntity(folkloreDto.getId(), folkloreDto.getName(), folkloreDto.getDescription(), folkloreDto.getMotifCode());
    }
}
