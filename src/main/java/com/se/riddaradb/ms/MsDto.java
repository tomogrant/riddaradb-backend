package com.se.riddaradb.ms;
import java.util.HashSet;
import java.util.Set;

public class MsDto {
        private Integer id;
        private String name;
        private String shelfmark;
        private String description;
        private Set<MsSagaDto> msSagaDtos = new HashSet<>();
        private Integer msRepositoryId;

        protected MsDto(){
        }

        public MsDto(Integer id,
                     String name,
                     String shelfmark,
                     String description) {
            this.id = id;
            this.name = name;
            this.shelfmark = shelfmark;
            this.description = description;
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

    public String getShelfmark() {
        return shelfmark;
    }

    public void setShelfmark(String shelfmark) {
        this.shelfmark = shelfmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MsSagaDto> getMsSagaDtos() {
        return msSagaDtos;
    }

    public void setMsSagaDtos(Set<MsSagaDto> msSagaDtos) {
        this.msSagaDtos = msSagaDtos;
    }

    public Integer getMsRepositoryId() {
        return msRepositoryId;
    }

    public void setMsRepositoryId(Integer msRepositoryId) {
        this.msRepositoryId = msRepositoryId;
    }
}
