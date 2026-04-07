package com.se.riddaradb.dtos;
import com.se.riddaradb.entities.ObjectEntity;

import java.util.HashSet;
import java.util.Set;

public class ObjectDto {
        int id;
        String name;
        String description;
        ObjectEntity.Type type;
        Set<Integer> SagaVersionIds;

        protected ObjectDto(){
        }

    public ObjectDto(int id,
                     String name,
                     String description,
                     ObjectEntity.Type type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        SagaVersionIds = new HashSet<>();
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

    public ObjectEntity.Type getType() {
        return type;
    }

    public void setType(ObjectEntity.Type type) {
        this.type = type;
    }

    public Set<Integer> getSagaVersionIds() {
        return SagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> SagaVersionIds) {
        this.SagaVersionIds = SagaVersionIds;
    }
}
