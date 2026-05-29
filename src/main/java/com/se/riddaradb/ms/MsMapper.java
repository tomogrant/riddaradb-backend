package com.se.riddaradb.ms;

import com.se.riddaradb.sagaversion.SagaVersionEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MsMapper {

    public MsDto mapToDto(MsEntity msEntity){
        MsDto msDto = new MsDto(msEntity.getId(), msEntity.getName(), msEntity.getDescription(), msEntity.getShelfMark());

        msDto.setSagaVersionIds(msEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return msDto;
    }

    public MsEntity mapFromDto(MsDto msDto){
        return new MsEntity(msDto.getId(), msDto.getName(), msDto.getDescription(), msDto.getShelfMark());
    }
}
