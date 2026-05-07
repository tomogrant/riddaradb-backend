package com.se.riddaradb.mappers;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.entities.BibEntity;
import com.se.riddaradb.entities.SagaVersionEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BibMapper {

    public BibDto mapToDto(BibEntity bibEntity){
        BibDto bibDto = new BibDto(bibEntity.getId(),
                bibEntity.getPublicationType(),
                bibEntity.getAuthors(),
                bibEntity.getEditors(),
                bibEntity.getTranslators(),
                bibEntity.getTitle(),
                bibEntity.getUrl(),
                bibEntity.getBookEditors(),
                bibEntity.getBook(),
                bibEntity.getBookSeries(),
                bibEntity.getVolume(),
                bibEntity.getNumOfVolumes(),
                bibEntity.getPlaceOfPublication(),
                bibEntity.getPublisher(),
                bibEntity.getPublicationYear(),
                bibEntity.getPageNumbers(),
                bibEntity.getRecommended());

        bibDto.setSagaVersionIds(bibEntity.getSagaVersionEntity()
                .stream()
                .map(SagaVersionEntity::getId)
                .collect(Collectors.toSet()));

        return bibDto;
    }

    public BibEntity mapFromDto(BibDto bibDto){
        return new BibEntity(bibDto.getId(),
                bibDto.getPublicationType(),
                bibDto.getAuthors(),
                bibDto.getEditors(),
                bibDto.getTranslators(),
                bibDto.getTitle(),
                bibDto.getUrl(),
                bibDto.getBookEditors(),
                bibDto.getBook(),
                bibDto.getBookSeries(),
                bibDto.getVolume(),
                bibDto.getNumOfVolumes(),
                bibDto.getPlaceOfPublication(),
                bibDto.getPublisher(),
                bibDto.getPublicationYear(),
                bibDto.getPageNumbers(),
                bibDto.getRecommended());
    }
}
