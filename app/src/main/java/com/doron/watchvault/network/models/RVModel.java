package com.doron.watchvault.network.models;

public class RVModel {
    private String id;
    private String imageUrl;
    private String title;
    private int releaseDay;
    private int releaseMonth;
    private int releaseYear;

    public RVModel(String id, String imageUrl, String title, int releaseDay, int releaseMonth, int releaseYear) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.releaseDay = releaseDay;
        this.releaseMonth = releaseMonth;
        this.releaseYear = releaseYear;
    }

    // Getters
    public String getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public String getTitle() { return title; }
    public int getReleaseDay() { return releaseDay; }
    public int getReleaseMonth() { return releaseMonth; }
    public int getReleaseYear() { return releaseYear; }
}
