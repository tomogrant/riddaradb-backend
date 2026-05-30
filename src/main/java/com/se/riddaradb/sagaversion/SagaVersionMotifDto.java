package com.se.riddaradb.sagaversion;

public class SagaVersionMotifDto {
    int motifId;
    String motifCode;
    String motifName;
    String pageChapterNumber;

    public SagaVersionMotifDto(int motifId, String motifCode, String motifName, String pageChapterNumber) {
        this.motifId = motifId;
        this.motifCode = motifCode;
        this.motifName = motifName;
        this.pageChapterNumber = pageChapterNumber;
    }

    public int getMotifId() {
        return motifId;
    }

    public void setMotifId(int motifId) {
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
