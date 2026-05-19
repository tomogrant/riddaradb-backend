package com.se.riddaradb.dtos;

import java.util.HashSet;
import java.util.Set;

public class MotifDto {

    int id;
    String motifCode;
    String motifName;
    String description;
    Integer parentId;
    Boolean hasChildren;
    Set<Integer> SagaVersionIds;

    public MotifDto(int id, String motifCode, String motifName, String description, Boolean hasChildren) {
        this.id = id;
        this.motifCode = motifCode;
        this.motifName = motifName;
        this.description = description;
        this.parentId = 0;
        this.hasChildren = hasChildren;
        SagaVersionIds = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Set<Integer> getSagaVersionIds() {
        return SagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> SagaVersionIds) {
        this.SagaVersionIds = SagaVersionIds;
    }
}
