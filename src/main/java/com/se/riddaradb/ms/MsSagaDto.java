package com.se.riddaradb.ms;

public class MsSagaDto {
    private Integer sagaId;
    private String folioNumber;

    public MsSagaDto(Integer sagaId, String folioNumber) {
        this.sagaId = sagaId;
        this.folioNumber = folioNumber;
    }

    public Integer getSagaId() {
        return sagaId;
    }

    public void setSagaId(Integer sagaId) {
        this.sagaId = sagaId;
    }

    public String getFolioNumber() {
        return folioNumber;
    }

    public void setFolioNumber(String folioNumber) {
        this.folioNumber = folioNumber;
    }
}
