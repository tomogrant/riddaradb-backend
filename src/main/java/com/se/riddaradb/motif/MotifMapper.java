package com.se.riddaradb.motif;

import com.se.riddaradb.sagaversion.SagaVersionMotifDto;
import com.se.riddaradb.sagaversion.SagaVersionMotifEntity;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Service;

@Service
public class MotifMapper {

    public MotifDto mapToDto(MotifEntity motifEntity){
        MotifDto motifDto = new MotifDto(motifEntity.getId(), motifEntity.getMotifCode(), motifEntity.getMotifName(), motifEntity.getDescription(), !motifEntity.getChildren().isEmpty());

        System.out.println("In motif mapper");

        for (SagaVersionMotifEntity sagaVersionMotifEntity : motifEntity.getSagaVersionMotifEntities()){
            motifDto.getSagaMotifs().add(new MotifSagaVersionDto
                    (sagaVersionMotifEntity.getSagaVersionEntity().getId(),
                    sagaVersionMotifEntity.getPageChapterNumber()));
        }

        if (motifEntity.getParent() != null){
            motifDto.setParentId(motifEntity.getParent().getId());
        }

        return motifDto;
    }

    public MotifEntity mapFromDto(MotifDto motifDto){

        return new MotifEntity(motifDto.getId(), motifDto.getMotifCode(), motifDto.getMotifName(), motifDto.getDescription());

    }
}
