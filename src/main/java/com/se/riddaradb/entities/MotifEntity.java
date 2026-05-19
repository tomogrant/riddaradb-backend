package com.se.riddaradb.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "motif")
public class MotifEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String motifCode;

    String motifName;

    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    MotifEntity parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    Set<MotifEntity> children = new HashSet<>();

    @ManyToMany(mappedBy = "motifEntity")
    Set<SagaVersionEntity> sagaVersionEntity = new HashSet<>();

    protected MotifEntity() {
    }

    public MotifEntity(int id,
                       String motifCode,
                       String motifName,
                       String description) {
        this.id = id;
        this.motifCode = motifCode;
        this.motifName = motifName;
        this.description = description;
    }

    public void addChildMotif(MotifEntity childMotif){
        this.getChildren().add(childMotif);
        childMotif.setParent(this);
    }

    public int getId() {
        return id;
    }

    public String getMotifCode() {
        return motifCode;
    }

    public void setMotifCode(String motifCode) {
        this.motifCode = motifCode;
    }

    public String getMotifName() {
        return motifName;
    }

    public void setMotifName(String motifName) {
        this.motifName = motifName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MotifEntity getParent() {
        return parent;
    }

    public void setParent(MotifEntity parent) {
        this.parent = parent;
    }

    public Set<MotifEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<MotifEntity> children) {
        this.children = children;
    }

    public Set<SagaVersionEntity> getSagaVersionEntity() {
        return sagaVersionEntity;
    }

    public void setSagaVersionEntity(Set<SagaVersionEntity> sagaVersionEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
    }

    public void setId(int id) {
        this.id = id;
    }
}
