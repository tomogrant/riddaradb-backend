package com.se.riddaradb.dtos;

import java.util.Collection;
import java.util.Set;

public class SagaResponseDto {

    int id;
    String title;
    String description;
    Collection<SagaVersionDto> sagaVersions;

    public SagaResponseDto(int id, String title, String description, Set<SagaVersionDto> sagaVersions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sagaVersions = sagaVersions;
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

    public Collection<SagaVersionDto> getSagaVersions() {
        return this.sagaVersions;
    }

    public void setSagaVersions(Collection<SagaVersionDto> sagaVersions) {
        this.sagaVersions = sagaVersions;
    }
}
