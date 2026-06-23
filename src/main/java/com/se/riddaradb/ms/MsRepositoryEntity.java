package com.se.riddaradb.ms;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class MsRepositoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "msRepositoryEntity", cascade = CascadeType.ALL)
    private Set<MsEntity> msEntities = new HashSet<>();

    public void addMs(MsEntity msEntity){
        this.msEntities.add(msEntity);
        msEntity.setMsRepositoryEntity(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MsEntity> getMsEntities() {
        return msEntities;
    }

    public void setMsEntities(Set<MsEntity> msEntities) {
        this.msEntities = msEntities;
    }
}
