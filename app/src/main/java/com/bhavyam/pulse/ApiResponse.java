package com.bhavyam.pulse;

import java.util.List;

public class ApiResponse {
    private List<SearchResult> item;

    public List<SearchResult> getItem() {
        return item;
    }

    public void setItem(List<SearchResult> item) {
        this.item = item;
    }
}