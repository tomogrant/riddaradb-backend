package com.se.riddaradb.saga;

import com.se.riddaradb.sagaversion.SagaVersionRequestDto;

import java.util.Set;

public class SagaRequestDto {

    int id;
    String title;
    String description;
    Boolean translated;
    Set<SagaVersionRequestDto> sagaVersions;
    Set<Integer> bibIds;

    public SagaRequestDto(int id, String title, String description, Boolean translated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.translated = translated;
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

    public void setTitle(String name) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTranslated() {
        return translated;
    }

    public void setTranslated(Boolean translated) {
        this.translated = translated;
    }

    public Set<SagaVersionRequestDto> getSagaVersions() {
        return sagaVersions;
    }

    public void setSagaVersions(Set<SagaVersionRequestDto> sagaVersions) {
        this.sagaVersions = sagaVersions;
    }

    public Set<Integer> getBibIds() {
        return bibIds;
    }

    public void setBibIds(Set<Integer> bibIds) {
        this.bibIds = bibIds;
    }
}
