package com.se.riddaradb.dtos;

import java.util.HashSet;
import java.util.Set;

public class SagaDto {

    int id;
    String title;
    String description;
    Set<Integer> sagaVersionIds;

    public SagaDto(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public Set<Integer> getSagaVersionIds() {
        return sagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> sagaVersionIds) {
        this.sagaVersionIds = sagaVersionIds;
    }
}
