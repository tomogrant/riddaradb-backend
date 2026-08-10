package com.se.riddaradb.sagaversion;

import java.util.HashSet;
import java.util.Set;

public class SagaVersionRequestDto {

    private Integer id;
    private String title;
    private String description;
    private SagaVersionEntity.SagaDate date;
    private Integer sagaId;
    private Set<Integer> motifIds;
    private Set<Integer> personIds;
    private Set<Integer> placeIds;
    private Set<Integer> objectIds;
    private Set<Integer> msIds;

    public SagaVersionRequestDto(Integer id, String title, String description, SagaVersionEntity.SagaDate date) {
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

    public SagaVersionRequestDto() {

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

    public Integer getSagaId() {
        return sagaId;
    }

    public void setSagaId(Integer sagaId) {
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
