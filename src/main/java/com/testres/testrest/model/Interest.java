package com.testres.testrest.model;

/**
 * @author konst
 */
public class Interest {
    private Long interestId;
    private Long personId;
    private String title;

    public Interest() {
    }

    public Interest(Long interestId, Long personId, String title) {
        this.interestId = interestId;
        this.personId = personId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "interestId=" + interestId +
                ", personId=" + personId +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
