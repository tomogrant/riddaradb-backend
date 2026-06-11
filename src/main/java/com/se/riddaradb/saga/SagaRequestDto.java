package com.se.riddaradb.saga;

import com.se.riddaradb.sagaversion.SagaVersionRequestDto;

import java.util.HashSet;
import java.util.Set;

public class SagaRequestDto {

    private Integer id;
    private String title;
    private String description;
    private Boolean translated;
    private Set<SagaVersionRequestDto> sagaVersions;
    private Set<Integer> bibIds = new HashSet<>();
    private Set<SagaMsDto> sagaMsDtos = new HashSet<>();

    public SagaRequestDto(Integer id, String title, String description, Boolean translated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.translated = translated;
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

    public Set<SagaMsDto> getSagaMsDtos() {
        return sagaMsDtos;
    }

    public void setSagaMsDtos(Set<SagaMsDto> sagaMsDtos) {
        this.sagaMsDtos = sagaMsDtos;
    }
}
