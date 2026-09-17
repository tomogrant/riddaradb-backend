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

    private String city;

    private String country;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<MsEntity> getMsEntities() {
        return msEntities;
    }

    public void setMsEntities(Set<MsEntity> msEntities) {
        this.msEntities = msEntities;
    }
}
