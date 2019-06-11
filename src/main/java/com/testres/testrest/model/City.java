package com.testres.testrest.model;

/**
 * @author konst
 */
public class City {
    private Long cityId;
    private String title;

    public City() {
    }

    public City(Long cityId, String title) {
        this.cityId = cityId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
