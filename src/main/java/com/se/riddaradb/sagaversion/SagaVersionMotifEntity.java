package com.se.riddaradb.sagaversion;

import com.se.riddaradb.motif.MotifEntity;
import jakarta.persistence.*;

@Entity
@Table(name="sagaversionmotif")
public class SagaVersionMotifEntity {

    @EmbeddedId
    SagaVersionMotifKey id = new SagaVersionMotifKey();

    @ManyToOne
    @MapsId("sagaVersionId")
    @JoinColumn(name="sagaversion_id")
    SagaVersionEntity sagaVersionEntity;

    @ManyToOne
    @MapsId("motifId")
    @JoinColumn(name="motif_id")
    MotifEntity motifEntity;

    String pageChapterNumber;

    public SagaVersionMotifEntity(){}

    public SagaVersionMotifEntity(SagaVersionEntity sagaVersionEntity, MotifEntity motifEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
        this.motifEntity = motifEntity;
        this.setId(new SagaVersionMotifKey(sagaVersionEntity.getId(), motifEntity.getId()));
    }

    public SagaVersionMotifEntity(SagaVersionEntity sagaVersionEntity, MotifEntity motifEntity, String pageChapterNumber) {
        this.sagaVersionEntity = sagaVersionEntity;
        this.motifEntity = motifEntity;
        this.pageChapterNumber = pageChapterNumber;
        this.setId(new SagaVersionMotifKey(sagaVersionEntity.getId(), motifEntity.getId()));
    }

    public SagaVersionMotifKey getId() {
        return id;
    }

    public void setId(SagaVersionMotifKey id) {
        this.id = id;
    }

    public SagaVersionEntity getSagaVersionEntity() {
        return sagaVersionEntity;
    }

    public void setSagaVersionEntity(SagaVersionEntity sagaVersionEntity) {
        this.sagaVersionEntity = sagaVersionEntity;
    }

    public MotifEntity getMotifEntity() {
        return motifEntity;
    }

    public void setMotifEntity(MotifEntity motifEntity) {
        this.motifEntity = motifEntity;
    }

    public String getPageChapterNumber() {
        return pageChapterNumber;
    }

    public void setPageChapterNumber(String pageChapterNumber) {
        this.pageChapterNumber = pageChapterNumber;
    }
}
