package com.testres.testrest.model;

/**
 * @author konst
 */
public class Country {
    private Long countryId;
    private String title;

    public Country() {
    }

    public Country(Long countryId, String title) {
        this.countryId = countryId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
