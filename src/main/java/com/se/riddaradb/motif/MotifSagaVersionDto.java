package com.se.riddaradb.motif;

public class MotifSagaVersionDto {
    private Integer sagaVersionId;
    private String pageChapterNumber;

    public MotifSagaVersionDto(Integer sagaVersionId, String pageChapterNumber) {
        this.sagaVersionId = sagaVersionId;
        this.pageChapterNumber = pageChapterNumber;
    }

    public Integer getSagaVersionId() {
        return sagaVersionId;
    }

    public void setSagaVersionId(Integer sagaVersionId) {
        this.sagaVersionId = sagaVersionId;
    }

    public String getPageChapterNumber() {
        return pageChapterNumber;
    }

    public void setPageChapterNumber(String pageChapterNumber) {
        this.pageChapterNumber = pageChapterNumber;
    }
}
