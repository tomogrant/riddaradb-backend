package com.se.riddaradb.sagaversion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SagaVersionMotifKey implements Serializable {

    public SagaVersionMotifKey(){}

    public SagaVersionMotifKey(int sagaVersionId, int motifId) {
        this.sagaVersionId = sagaVersionId;
        this.motifId = motifId;
    }

    @Column(name="sagaversion_id")
    int sagaVersionId;

    @Column(name="motif_id")
    int motifId;

    public int getSagaVersionId() {
        return sagaVersionId;
    }

    public void setSagaVersionId(int sagaVersionId) {
        this.sagaVersionId = sagaVersionId;
    }

    public int getMotifId() {
        return motifId;
    }

    public void setMotifId(int motifId) {
        this.motifId = motifId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SagaVersionMotifKey that = (SagaVersionMotifKey) o;
        return getSagaVersionId() == that.getSagaVersionId() && getMotifId() == that.getMotifId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSagaVersionId(), getMotifId());
    }
}