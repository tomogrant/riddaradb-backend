package com.se.riddaradb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OBJECT")
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String description;

    @Enumerated(EnumType.STRING)
    Type type;

    public enum Type {
        ARMOUR, WEAPON, JEWELLERY, CLOTHING, OTHER
    }

    @ManyToMany(mappedBy = "objectEntity")
    Set<SagaVersionEntity> sagaVersionEntity = new HashSet<>();

    protected ObjectEntity() {
    }

    public ObjectEntity(int id, String name, String description, Type type) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<SagaVersionEntity> getSagaVersionEntity() {
        return sagaVersionEntity;
    }

    public void setSagaVersionEntity(Set<SagaVersionEntity> sagaVersionEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
    }
}