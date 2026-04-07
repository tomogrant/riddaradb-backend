package com.se.riddaradb.dtos;
import java.util.HashSet;
import java.util.Set;

public class MsDto {
        int id;
        String name;
        String shelfMark;
        String description;
        Set<Integer> SagaVersionIds;

        protected MsDto(){
        }

    public MsDto(int id,
                 String name,
                 String shelfMark,
                 String description) {
        this.id = id;
        this.name = name;
        this.shelfMark = shelfMark;
        this.description = description;
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

    public String getShelfMark() {
        return shelfMark;
    }

    public void setShelfMark(String shelfMark) {
        this.shelfMark = shelfMark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Integer> getSagaVersionIds() {
        return SagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> SagaVersionIds) {
        this.SagaVersionIds = SagaVersionIds;
    }
}
