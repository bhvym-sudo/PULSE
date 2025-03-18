package com.bhavyam.pulse;

public class NewsItem {
    private String title;
    private String region;
    private String description;

    
    public NewsItem(String title, String region, String description) {
        this.title = title;
        this.region = region;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getRegion() {
        return region;
    }

    public String getDescription() {
        return description;
    }
}