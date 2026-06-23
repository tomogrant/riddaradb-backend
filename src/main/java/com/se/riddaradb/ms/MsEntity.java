package com.se.riddaradb.ms;

import com.se.riddaradb.saga.SagaEntity;
import com.se.riddaradb.saga.SagaMsEntity;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import com.se.riddaradb.sagaversion.SagaVersionMotifEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ms")
public class MsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String shelfmark;

    private String description;

    @OneToMany(mappedBy = "msEntity", orphanRemoval = true)
    private Set<SagaMsEntity> sagaMsEntities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="msRepositoryId")
    private MsRepositoryEntity msRepositoryEntity;

    protected MsEntity() {
    }

    public MsEntity(Integer id, String name, String shelfmark, String description) {
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

    public Set<SagaMsEntity> getSagaMsEntities() {
        return sagaMsEntities;
    }

    public void setSagaMsEntities(Set<SagaMsEntity> sagaMsEntities) {
        this.sagaMsEntities = sagaMsEntities;
    }

    public MsRepositoryEntity getMsRepositoryEntity() {
        return msRepositoryEntity;
    }

    public void setMsRepositoryEntity(MsRepositoryEntity msRepositoryEntity) {
        this.msRepositoryEntity = msRepositoryEntity;
    }
}
