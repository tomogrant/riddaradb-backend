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

        //Set saga versions
        sagaResponseDto.setSagaVersions(sagaEntity.getSagaVersionEntities()
                .stream()
                .map(sagaVersionMapper::mapToDto)
                .collect(Collectors.toSet()));

        //Set bibliography entries
        sagaResponseDto.setBibDto(sagaEntity.getBibEntities()
                .stream()
                .map(bibMapper::mapToDto)
                .collect(Collectors.toSet()));

        //Set MS entries
        for (SagaMsEntity sagaMsEntity : sagaEntity.getSagaMsEntities()){
            sagaResponseDto.getSagaMsDtos().add(new SagaMsDto
                    (sagaMsEntity.getMsEntity().getId(),
                            sagaMsEntity.getMsEntity().getShelfmark(),
                            sagaMsEntity.getFolioNumber()));
        }

        return sagaResponseDto;
    }

    public SagaTitleDto mapToSagaTitle(SagaEntity sagaEntity){
        return new SagaTitleDto(sagaEntity.getId(), sagaEntity.getTitle());
    }
}
