package com.se.riddaradb.sagaversion;

public class SagaVersionMotifDto {
    private Integer motifId;
    private String motifCode;
    private String motifName;
    private String pageChapterNumber;

    public SagaVersionMotifDto(Integer motifId, String motifCode, String motifName, String pageChapterNumber) {
        this.motifId = motifId;
        this.motifCode = motifCode;
        this.motifName = motifName;
        this.pageChapterNumber = pageChapterNumber;
    }

    public Integer getMotifId() {
        return motifId;
    }

    public void setMotifId(Integer motifId) {
        this.motifId = motifId;
    }

    public String getMotifCode() {
        return motifCode;
    }

    public void setMotifCode(String motifCode) {
        this.motifCode = motifCode;
    }

    public String getMotifName() {
        return motifName;
    }

    public void setMotifName(String motifName) {
        this.motifName = motifName;
    }

    public String getPageChapterNumber() {
        return pageChapterNumber;
    }

    public void setPageChapterNumber(String pageChapterNumber) {
        this.pageChapterNumber = pageChapterNumber;
    }
}
