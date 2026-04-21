package com.se.riddaradb.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SAGAVERSION")
public class SagaVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String description;

    int date;

    Boolean isTranslated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sagaId")
    SagaEntity sagaEntity;

    @ManyToMany()
    @JoinTable(name = "sagaversion-bib",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "bib_id"))
    Set<BibEntity> bibEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-folklore",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "folklore_id"))
    Set<FolkloreEntity> folkloreEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-person",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    Set<PersonEntity> personEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-place",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    Set<PlaceEntity> placeEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-object",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id"))
    Set<ObjectEntity> objectEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-ms",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "ms_id"))
    Set<MsEntity> msEntity = new HashSet<>();

    protected SagaVersionEntity() {
    }

    public SagaVersionEntity(int id, String title, String description, int date, Boolean isTranslated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.isTranslated = isTranslated;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Boolean getIsTranslated() {
        return isTranslated;
    }

    public void setIsTranslated(Boolean isTranslated) {
        this.isTranslated = isTranslated;
    }

    public SagaEntity getSagaEntity() {
        return sagaEntity;
    }

    public void setSagaEntity(SagaEntity sagaEntity) {
        this.sagaEntity = sagaEntity;
    }

    public Set<BibEntity> getBibEntity() {
        return bibEntity;
    }

    public void setBibEntity(Set<BibEntity> bibEntity) {
        this.bibEntity = bibEntity;
    }

    public Set<FolkloreEntity> getFolkloreEntity() {
        return folkloreEntity;
    }

    public void setFolkloreEntity(Set<FolkloreEntity> folkloreEntity) {
        this.folkloreEntity = folkloreEntity;
    }

    public Set<PersonEntity> getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(Set<PersonEntity> personEntity) {
        this.personEntity = personEntity;
    }

    public Set<PlaceEntity> getPlaceEntity() {
        return placeEntity;
    }

    public void setPlaceEntity(Set<PlaceEntity> placeEntity) {
        this.placeEntity = placeEntity;
    }

    public Set<ObjectEntity> getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(Set<ObjectEntity> objectEntity) {
        this.objectEntity = objectEntity;
    }

    public Set<MsEntity> getMsEntity() {
        return msEntity;
    }

    public void setMsEntity(Set<MsEntity> msEntity) {
        this.msEntity = msEntity;
    }
}
