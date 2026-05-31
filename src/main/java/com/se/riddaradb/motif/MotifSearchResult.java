package com.se.riddaradb.motif;

import java.util.Set;

public class MotifSearchResult {
    Integer searchResultId;
    Set<Integer> searchResultPath;

    public MotifSearchResult(Integer searchResultId, Set<Integer> searchResultPath) {
        this.searchResultId = searchResultId;
        this.searchResultPath = searchResultPath;
    }

    public Integer getSearchResultId() {
        return searchResultId;
    }

    public void setSearchResultId(Integer searchResultId) {
        this.searchResultId = searchResultId;
    }

    public Set<Integer> getSearchResultPath() {
        return searchResultPath;
    }

    public void setSearchResultPath(Set<Integer> searchResultPath) {
        this.searchResultPath = searchResultPath;
    }
}
