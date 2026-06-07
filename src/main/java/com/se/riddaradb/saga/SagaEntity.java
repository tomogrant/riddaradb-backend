package com.se.riddaradb.saga;

import com.se.riddaradb.bib.BibEntity;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "saga")
public class SagaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    Boolean translated;

    @OneToMany(mappedBy = "sagaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<SagaVersionEntity> sagaVersionEntities = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-bib",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "bib_id"))
    private Set<BibEntity> bibEntity = new HashSet<>();

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
}
