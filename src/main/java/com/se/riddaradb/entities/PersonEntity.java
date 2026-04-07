package com.se.riddaradb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Enumerated(EnumType.STRING)
    PersonEntity.Species species;

    public enum Species {
        HUMAN, DVERGR, JOTUNN, RISI, TROLL
    }

    String realWorldRef;

    @ManyToMany(mappedBy = "personEntity")
    Set<PlaceEntity> placeEntity = new HashSet<>();

    @ManyToMany(mappedBy = "personEntity")
    Set<SagaVersionEntity> sagaVersionEntity = new HashSet<>();

    protected PersonEntity() {
    }

    public PersonEntity(int id, String name, Species species, String realWorldRef) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.realWorldRef = realWorldRef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getRealWorldRef() {
        return realWorldRef;
    }

    public void setRealWorldRef(String realWorldRef) {
        this.realWorldRef = realWorldRef;
    }

    public Set<PlaceEntity> getPlaceEntity() {
        return placeEntity;
    }

    public void setPlaceEntity(Set<PlaceEntity> placeEntity) {
        this.placeEntity = placeEntity;
    }

    public Set<SagaVersionEntity> getSagaVersionEntity() {
        return sagaVersionEntity;
    }

    public void setSagaVersionEntity(Set<SagaVersionEntity> sagaVersionEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
    }


}


