package com.se.riddaradb.saga;

import com.se.riddaradb.bib.BibEntity;
import com.se.riddaradb.motif.MotifEntity;
import com.se.riddaradb.ms.MsEntity;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import com.se.riddaradb.sagaversion.SagaVersionMotifEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "saga")
public class SagaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean translated;

    @OneToMany(mappedBy = "sagaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SagaVersionEntity> sagaVersionEntities = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "saga-bib",
            joinColumns = @JoinColumn(name = "saga_id"),
            inverseJoinColumns = @JoinColumn(name = "bib_id"))
    private Set<BibEntity> bibEntity = new HashSet<>();

    @OneToMany(mappedBy = "sagaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SagaMsEntity> sagaMsEntities = new HashSet<>();

    protected SagaEntity(){
    }

    public SagaEntity(Integer id, String title, String description, Boolean translated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.translated = translated;
    }


    public void addSagaVersion(SagaVersionEntity sagaVersion){
        sagaVersionEntities.add(sagaVersion);
        sagaVersion.setSagaEntity(this);
    }

    public void removeSagaVersion(int id){
        getSagaVersionEntities().removeIf(existingSagaVersionEntity -> existingSagaVersionEntity.getId() == id);
    }

    public void addBib(BibEntity bib){
        bibEntity.add(bib);
        bib.getSagaEntity().add(this);
    }

    public void addMs(MsEntity msEntity, String pageChapterNumber){
        SagaMsEntity sagaMsEntity = new SagaMsEntity(this, msEntity, pageChapterNumber);

        getSagaMsEntities().add(sagaMsEntity);
        msEntity.getSagaMsEntities().add(sagaMsEntity);

        System.out.println("Saga-MS entity created with ID: " + sagaMsEntity.getSagaEntity().getId() + sagaMsEntity.getMsEntity().getId());
    }

    public void removeMs(MsEntity msEntity){
        getSagaMsEntities().removeIf(sagaMs -> Objects.equals(sagaMs.getMsEntity().getId(), msEntity.getId()));
        msEntity.getSagaMsEntities().removeIf(sagaMs -> Objects.equals(sagaMs.getSagaEntity().getId(), getId()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTranslated() {
        return translated;
    }

    public void setTranslated(Boolean translated) {
        this.translated = translated;
    }

    public Set<SagaVersionEntity> getSagaVersionEntities() {
        return sagaVersionEntities;
    }

    public void setSagaVersionEntities(Set<SagaVersionEntity> sagaVersionEntities) {
        this.sagaVersionEntities = sagaVersionEntities;
    }

    public Set<BibEntity> getBibEntity() {
        return bibEntity;
    }

    public void setBibEntity(Set<BibEntity> bibEntity) {
        this.bibEntity = bibEntity;
    }

    public Set<SagaMsEntity> getSagaMsEntities() {
        return sagaMsEntities;
    }

    public void setSagaMsEntities(Set<SagaMsEntity> sagaMsEntities) {
        this.sagaMsEntities = sagaMsEntities;
    }
}
