package com.se.riddaradb.dtos;

import com.se.riddaradb.entities.SagaVersionEntity;

import java.util.HashSet;
import java.util.Set;

public class SagaVersionResponseDto {

    int id;
    String title;
    String description;
    SagaVersionEntity.SagaDate date;
    Boolean isTranslated;
    Integer sagaId;
    Set<BibDto> bibDto;
    Set<Integer> folkloreIds;
    Set<Integer> personIds;
    Set<Integer> placeIds;
    Set<Integer> objectIds;
    Set<Integer> msIds;

    public SagaVersionResponseDto(int id, String title, String description, SagaVersionEntity.SagaDate date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        folkloreIds = new HashSet<>();
        personIds = new HashSet<>();
        placeIds = new HashSet<>();
        objectIds = new HashSet<>();
        msIds = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SagaVersionEntity.SagaDate getDate() {
        return date;
    }

    public void setDate(SagaVersionEntity.SagaDate date) {
        this.date = date;
    }

    public void setIsTranslated(Boolean isTranslated) {
        this.isTranslated = isTranslated;
    }

    public int getSagaId() {
        return sagaId;
    }

    public void setSagaId(int sagaId) {
        this.sagaId = sagaId;
    }

    public Set<BibDto> getBibDto() {
        return bibDto;
    }

    public void setBibDto(Set<BibDto> bibDto) {
        this.bibDto = bibDto;
    }

    public Set<Integer> getFolkloreIds() {
        return folkloreIds;
    }

    public void setFolkloreIds(Set<Integer> folkloreIds) {
        this.folkloreIds = folkloreIds;
    }

    public Set<Integer> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(Set<Integer> personIds) {
        this.personIds = personIds;
    }

    public Set<Integer> getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(Set<Integer> placeIds) {
        this.placeIds = placeIds;
    }

    public Set<Integer> getObjectIds() {
        return objectIds;
    }

    public void setObjectIds(Set<Integer> objectIds) {
        this.objectIds = objectIds;
    }

    public Set<Integer> getMsIds() {
        return msIds;
    }

    public void setMsIds(Set<Integer> msIds) {
        this.msIds = msIds;
    }
}
