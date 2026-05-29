package com.se.riddaradb.sagaversion;

public class SagaVersionMotifDto {
    int motifId;
    String pageChapterNumber;

    public SagaVersionMotifDto(int motifId, String pageChapterNumber) {
        this.motifId = motifId;
        this.pageChapterNumber = pageChapterNumber;
    }

    public int getMotifId() {
        return motifId;
    }

    public void setMotifId(int motifId) {
        this.motifId = motifId;
    }

    public String getPageChapterNumber() {
        return pageChapterNumber;
    }

    public void setPageChapterNumber(String pageChapterNumber) {
        this.pageChapterNumber = pageChapterNumber;
    }
}
