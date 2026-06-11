package com.se.riddaradb.saga;

import com.se.riddaradb.bib.BibDto;
import com.se.riddaradb.sagaversion.SagaVersionResponseDto;

import java.util.HashSet;
import java.util.Set;

public class SagaResponseDto {

    private Integer id;
    private String title;
    private String description;
    private Boolean translated;
    private Set<SagaVersionResponseDto> sagaVersions;
    private Set<BibDto> bibDto;
    private Set<SagaMsDto> sagaMsDtos = new HashSet<>();

    public SagaResponseDto(Integer id, String title, String description, Boolean translated) {
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

    public Set<SagaVersionResponseDto> getSagaVersions() {
        return this.sagaVersions;
    }

    public void setSagaVersions(Set<SagaVersionResponseDto> sagaVersions) {
        this.sagaVersions = sagaVersions;
    }

    public Set<BibDto> getBibDto() {
        return bibDto;
    }

    public void setBibDto(Set<BibDto> bibDto) {
        this.bibDto = bibDto;
    }

    public Set<SagaMsDto> getSagaMsDtos() {
        return sagaMsDtos;
    }

    public void setSagaMsDtos(Set<SagaMsDto> sagaMsDtos) {
        this.sagaMsDtos = sagaMsDtos;
    }
}
