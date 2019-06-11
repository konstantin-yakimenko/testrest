package com.testres.testrest.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author konst
 */
public class PersonEntity {

    Long personId;
    String name;
    String sex;
    LocalDate birth;
    String email;
    ZonedDateTime approveEmail;
    String phone;
    ZonedDateTime approvePhone;
    Long countryId;
    Long cityId;
    ZonedDateTime createDate;
    String status;
    ZonedDateTime premiumStart;
    ZonedDateTime premiumFinish;
    String about;
    List<Interest> interests;

    public PersonEntity(Person person) {
        this(person.getPersonId(),
                person.getName(),
                person.getSex(),
                person.getBirth(),
                person.getEmail(),
                person.getApproveEmail(),
                person.getPhone(),
                person.getApprovePhone(),
                person.getCountryId(),
                person.getCityId(),
                person.getCreateDate(),
                person.getStatus(),
                person.getPremiumStart(),
                person.getPremiumFinish(),
                person.getAbout(),
                new ArrayList<>());
    }



    public PersonEntity(Long personId, String name, String sex, LocalDate birth, String email, ZonedDateTime approveEmail, String phone, ZonedDateTime approvePhone, Long countryId, Long cityId, ZonedDateTime createDate, String status, ZonedDateTime premiumStart, ZonedDateTime premiumFinish, String about, List<Interest> interests) {
        this.personId = personId;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.email = email;
        this.approveEmail = approveEmail;
        this.phone = phone;
        this.approvePhone = approvePhone;
        this.countryId = countryId;
        this.cityId = cityId;
        this.createDate = createDate;
        this.status = status;
        this.premiumStart = premiumStart;
        this.premiumFinish = premiumFinish;
        this.about = about;
        this.interests = interests;
    }

    public Person toPerson() {
        return new Person.Buider()
                .setPersonId(personId)
                .setName(name)
                .setSex(sex)
                .setBirth(birth)
                .setEmail(email == null ? "" : email)
                .setApproveEmail(approveEmail)
                .setPhone(phone == null ? "" : phone)
                .setApprovePhone(approvePhone)
                .setCountryId(countryId == null ? 0 : countryId)
                .setCityId(cityId == null ? 0 : cityId)
                .setCreateDate(createDate)
                .setStatus(status == null ? "" : status)
                .setPremiumStart(premiumStart)
                .setPremiumFinish(premiumFinish)
                .setAbout(about == null ? "" : about)
                .build();
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", email='" + email + '\'' +
                ", approveEmail=" + approveEmail +
                ", phone='" + phone + '\'' +
                ", approvePhone=" + approvePhone +
                ", countryId=" + countryId +
                ", cityId=" + cityId +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", premiumStart=" + premiumStart +
                ", premiumFinish=" + premiumFinish +
                ", about='" + about + '\'' +
                ", interests=" + interests +
                '}';
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getApproveEmail() {
        return approveEmail;
    }

    public void setApproveEmail(ZonedDateTime approveEmail) {
        this.approveEmail = approveEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ZonedDateTime getApprovePhone() {
        return approvePhone;
    }

    public void setApprovePhone(ZonedDateTime approvePhone) {
        this.approvePhone = approvePhone;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getPremiumStart() {
        return premiumStart;
    }

    public void setPremiumStart(ZonedDateTime premiumStart) {
        this.premiumStart = premiumStart;
    }

    public ZonedDateTime getPremiumFinish() {
        return premiumFinish;
    }

    public void setPremiumFinish(ZonedDateTime premiumFinish) {
        this.premiumFinish = premiumFinish;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
}
