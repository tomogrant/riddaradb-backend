package com.se.riddaradb.sagaversion;

import java.util.HashSet;
import java.util.Set;

public class SagaVersionRequestDto {

    Integer id;
    String title;
    String description;
    SagaVersionEntity.SagaDate date;
    Integer sagaId;
    Set<Integer> motifIds;
    Set<Integer> personIds;
    Set<Integer> placeIds;
    Set<Integer> objectIds;
    Set<Integer> msIds;

    public SagaVersionRequestDto(Integer id, String title, String description, SagaVersionEntity.SagaDate date, Boolean isTranslated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        motifIds = new HashSet<>();
        personIds = new HashSet<>();
        placeIds = new HashSet<>();
        objectIds = new HashSet<>();
        msIds = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getSagaId() {
        return sagaId;
    }

    public void setSagaId(int sagaId) {
        this.sagaId = sagaId;
    }

    public Set<Integer> getMotifIds() {
        return motifIds;
    }

    public void setMotifIds(Set<Integer> motifIds) {
        this.motifIds = motifIds;
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
