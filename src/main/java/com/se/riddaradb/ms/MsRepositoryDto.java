package com.se.riddaradb.ms;

import java.util.Set;

public class MsRepositoryDto {

    private Integer id;
    private String name;
    private Set<Integer> msIds;

    public MsRepositoryDto(){

    }
    public MsRepositoryDto(Integer id, String name){
        this.id = id;
        this.name = name;
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

    public Set<Integer> getMsIds() {
        return msIds;
    }

    public void setMsIds(Set<Integer> msIds) {
        this.msIds = msIds;
    }
}
