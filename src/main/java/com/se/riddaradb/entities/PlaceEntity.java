package com.se.riddaradb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PLACE")
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Enumerated(EnumType.STRING)
    Type type;

    public enum Type {
        COUNTRY, SETTLEMENT, SITE, OTHER
    }

    @ManyToMany()
    @JoinTable(name = "place-person",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    Set<PersonEntity> personEntity = new HashSet<>();

    @ManyToMany(mappedBy = "placeEntity")
    Set<SagaVersionEntity> sagaVersionEntity = new HashSet<>();

    protected PlaceEntity() {
    }

    public PlaceEntity(int id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<PersonEntity> getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(Set<PersonEntity> personEntity) {
        this.personEntity = personEntity;
    }

    public Set<SagaVersionEntity> getSagaVersionEntity() {
        return sagaVersionEntity;
    }

    public void setSagaVersionEntity(Set<SagaVersionEntity> sagaVersionEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
    }
}