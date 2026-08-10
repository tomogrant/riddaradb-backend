package com.se.riddaradb.saga;

public class SagaMsDto {
    private Integer msId;
    private String shelfmark;
    private String folioNumber;

    public SagaMsDto(){}

    public SagaMsDto(Integer msId, String shelfmark, String folioNumber) {
        this.msId = msId;
        this.shelfmark = shelfmark;
        this.folioNumber = folioNumber;
    }

    public SagaMsDto(Integer msId) {
        this.msId = msId;
    }

    public Integer getMsId() {
        return msId;
    }

    public void setMsId(Integer msId) {
        this.msId = msId;
    }

    public String getShelfmark() {
        return shelfmark;
    }

    public void setShelfmark(String shelfmark) {
        this.shelfmark = shelfmark;
    }

    public String getFolioNumber() {
        return folioNumber;
    }

    public void setFolioNumber(String folioNumber) {
        this.folioNumber = folioNumber;
    }
}
