package com.se.riddaradb.sagaversion;

import com.se.riddaradb.bib.BibEntity;
import com.se.riddaradb.character.PersonEntity;
import com.se.riddaradb.motif.MotifEntity;
import com.se.riddaradb.ms.MsEntity;
import com.se.riddaradb.object.ObjectEntity;
import com.se.riddaradb.place.PlaceEntity;
import com.se.riddaradb.saga.SagaEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sagaversion")
public class SagaVersionEntity {

    public enum SagaDate {
        UNDEFINED,
        UNKNOWN,
        _1250_1300,
        _1300_1350,
        _1350_1400,
        _1400_1450,
        _1450_1500,
        _1500_1550
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    SagaDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sagaId")
    SagaEntity sagaEntity;

    @ManyToMany()
    @JoinTable(name = "sagaversion-bib",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "bib_id"))
    Set<BibEntity> bibEntity = new HashSet<>();

    @OneToMany(mappedBy = "sagaVersionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<SagaVersionMotifEntity> sagaVersionMotifEntities = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-person",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    Set<PersonEntity> personEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-place",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    Set<PlaceEntity> placeEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-object",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id"))
    Set<ObjectEntity> objectEntity = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "sagaversion-ms",
            joinColumns = @JoinColumn(name = "sagaversion_id"),
            inverseJoinColumns = @JoinColumn(name = "ms_id"))
    Set<MsEntity> msEntity = new HashSet<>();

    protected SagaVersionEntity() {
    }

    public SagaVersionEntity(int id, String title, String description, SagaDate date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public void addBib(BibEntity bib){
        bibEntity.add(bib);
        bib.getSagaVersionEntity().add(this);
    }

    public void addMotif(MotifEntity motifEntity, String pageChapterNumber){
        SagaVersionMotifEntity sagaVersionMotifEntity = new SagaVersionMotifEntity(this, motifEntity, pageChapterNumber);
        getSagaVersionMotifEntities().add(sagaVersionMotifEntity);
        motifEntity.getSagaVersionMotifEntities().add(sagaVersionMotifEntity);
    }

    public void removeMotif(MotifEntity motifEntity){
        getSagaVersionMotifEntities().removeIf(sagaMotif -> sagaMotif.getMotifEntity().getId() == motifEntity.getId());
        motifEntity.getSagaVersionMotifEntities().removeIf(sagaMotif -> sagaMotif.getSagaVersionEntity().getId() == getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SagaDate getDate() {
        return date;
    }

    public void setDate(SagaDate sagaDate) {
        this.date = sagaDate;
    }

    public SagaEntity getSagaEntity() {
        return sagaEntity;
    }

    public void setSagaEntity(SagaEntity sagaEntity) {
        this.sagaEntity = sagaEntity;
    }

    public Set<BibEntity> getBibEntity() {
        return bibEntity;
    }

    public void setBibEntity(Set<BibEntity> bibEntity) {
        this.bibEntity = bibEntity;
    }

    public Set<SagaVersionMotifEntity> getSagaVersionMotifEntities() {
        return sagaVersionMotifEntities;
    }

    public void setSagaVersionMotifEntities(Set<SagaVersionMotifEntity> sagaVersionMotifEntities) {
        this.sagaVersionMotifEntities = sagaVersionMotifEntities;
    }

    public Set<PersonEntity> getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(Set<PersonEntity> personEntity) {
        this.personEntity = personEntity;
    }

    public Set<PlaceEntity> getPlaceEntity() {
        return placeEntity;
    }

    public void setPlaceEntity(Set<PlaceEntity> placeEntity) {
        this.placeEntity = placeEntity;
    }

    public Set<ObjectEntity> getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(Set<ObjectEntity> objectEntity) {
        this.objectEntity = objectEntity;
    }

    public Set<MsEntity> getMsEntity() {
        return msEntity;
    }

    public void setMsEntity(Set<MsEntity> msEntity) {
        this.msEntity = msEntity;
    }
}
