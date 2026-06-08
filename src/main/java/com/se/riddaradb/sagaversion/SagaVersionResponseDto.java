package com.se.riddaradb.sagaversion;

import com.se.riddaradb.bib.BibDto;

import java.util.HashSet;
import java.util.Set;

public class SagaVersionResponseDto {

    private Integer id;
    private String title;
    private String description;
    private SagaVersionEntity.SagaDate date;
    private Integer sagaId;
    private Set<SagaVersionMotifDto> sagaMotifs;
    private Set<Integer> personIds;
    private Set<Integer> placeIds;
    private Set<Integer> objectIds;
    private Set<Integer> msIds;

    public SagaVersionResponseDto(Integer id, String title, String description, SagaVersionEntity.SagaDate date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        sagaMotifs = new HashSet<>();
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

    public Set<SagaVersionMotifDto> getSagaMotifs() {
        return sagaMotifs;
    }

    public void setSagaMotifs(Set<SagaVersionMotifDto> sagaMotifs) {
        this.sagaMotifs = sagaMotifs;
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
