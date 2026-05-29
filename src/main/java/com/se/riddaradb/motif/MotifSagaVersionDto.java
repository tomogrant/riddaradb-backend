package com.se.riddaradb.motif;

public class MotifSagaVersionDto {
    int sagaVersionId;
    String pageChapterNumber;

    public MotifSagaVersionDto(int sagaVersionId, String pageChapterNumber) {
        this.sagaVersionId = sagaVersionId;
        this.pageChapterNumber = pageChapterNumber;
    }

    public int getSagaVersionId() {
        return sagaVersionId;
    }

    public void setSagaVersionId(int sagaVersionId) {
        this.sagaVersionId = sagaVersionId;
    }

    public String getPageChapterNumber() {
        return pageChapterNumber;
    }

    public void setPageChapterNumber(String pageChapterNumber) {
        this.pageChapterNumber = pageChapterNumber;
    }
}
