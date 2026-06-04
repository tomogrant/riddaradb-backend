package com.se.riddaradb.sagaversion;

public class SagaVersionTitleDto {
    int id;
    int sagaId;
    String title;

    public SagaVersionTitleDto(int id, int sagaId, String title) {
        this.id = id;
        this.sagaId = sagaId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSagaId() {
        return sagaId;
    }

    public void setSagaId(int sagaId) {
        this.sagaId = sagaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
