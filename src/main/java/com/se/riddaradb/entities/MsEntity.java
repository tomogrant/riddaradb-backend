package com.se.riddaradb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MS")
public class MsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String shelfMark;

    String description;

    @ManyToMany(mappedBy = "msEntity")
    Set<SagaVersionEntity> sagaVersionEntity = new HashSet<>();

    protected MsEntity() {
    }

    public MsEntity(int id, String name, String description, String shelfMark) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shelfMark = shelfMark;
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

    public String getShelfMark() {
        return shelfMark;
    }

    public void setShelfMark(String shelfMark) {
        this.shelfMark = shelfMark;
    }

    public Set<SagaVersionEntity> getSagaVersionEntity() {
        return sagaVersionEntity;
    }

    public void setSagaVersionEntity(Set<SagaVersionEntity> sagaVersionEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
    }
}