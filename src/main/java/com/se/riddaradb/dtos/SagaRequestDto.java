package com.se.riddaradb.dtos;

import java.util.Set;

public class SagaRequestDto {

    int id;
    String title;
    String description;
    Boolean translated;
    Set<Integer> sagaVersionIds;

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

    public Set<Integer> getSagaVersionIds() {
        return sagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> sagaVersionIds) {
        this.sagaVersionIds = sagaVersionIds;
    }
}
