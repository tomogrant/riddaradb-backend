package com.se.riddaradb.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SAGA")
public class SagaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String title;

    String description;

    Boolean translated;

    @OneToMany(mappedBy = "sagaEntity", cascade = CascadeType.ALL)
    Set<SagaVersionEntity> sagaVersionEntities = new HashSet<>();

    protected SagaEntity(){
    }

    public SagaEntity(int id, String title, String description, Boolean translated) {
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

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
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

    public Set<SagaVersionEntity> getSagaVersionEntities() {
        return sagaVersionEntities;
    }

    public void setSagaVersionEntities(Set<SagaVersionEntity> sagaVersionEntities) {
        this.sagaVersionEntities = sagaVersionEntities;
    }
}
