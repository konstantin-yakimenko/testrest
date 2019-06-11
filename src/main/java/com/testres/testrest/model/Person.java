package com.testres.testrest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author konst
 */
public class Person {

    private Long personId;
    private String name;
    private String sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private Long countryId;
    private Long cityId;
    private String status;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private ZonedDateTime approveEmail;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private ZonedDateTime approvePhone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private ZonedDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private ZonedDateTime premiumStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private ZonedDateTime premiumFinish;
    private String about;
    private List<String> interests = new ArrayList<>();

    public Person() {
    }

    public Person(Long personId, String name, String sex, LocalDate birth, Long countryId, Long cityId, String status, String email, ZonedDateTime approveEmail, String phone, ZonedDateTime approvePhone, ZonedDateTime createDate, ZonedDateTime premiumStart, ZonedDateTime premiumFinish, String about, List<String> interests) {
        this.personId = personId;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.countryId = countryId;
        this.cityId = cityId;
        this.status = status;
        this.email = email;
        this.approveEmail = approveEmail;
        this.phone = phone;
        this.approvePhone = approvePhone;
        this.createDate = createDate;
        this.premiumStart = premiumStart;
        this.premiumFinish = premiumFinish;
        this.about = about;
        this.interests = interests;
    }

    public static class Buider {
        private Long personId = 0L;
        private String name = "";
        private String sex = "";
        private LocalDate birth;
        private Long countryId = 0L;
        private Long cityId = 0L;
        private String status = "";
        private String email = "";
        private ZonedDateTime approveEmail = null;
        private String phone = "";
        private ZonedDateTime approvePhone = null;
        private ZonedDateTime createDate = null;
        private ZonedDateTime premiumStart = null;
        private ZonedDateTime premiumFinish = null;
        private String about = "";
        private List<String> interests = new ArrayList<>();

        public Person build() {
            return new Person(personId,name,sex,birth,countryId,cityId,status,email,approveEmail,phone,approvePhone,createDate,premiumStart,premiumFinish,about,interests);
        }

        public Buider setPersonId(Long personId) {
            this.personId = personId;
            return this;
        }

        public Buider setName(String name) {
            this.name = name;
            return this;
        }

        public Buider setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Buider setBirth(LocalDate birth) {
            this.birth = birth;
            return this;
        }

        public Buider setCountryId(Long countryId) {
            this.countryId = countryId;
            return this;
        }

        public Buider setCityId(Long cityId) {
            this.cityId = cityId;
            return this;
        }

        public Buider setStatus(String status) {
            this.status = status;
            return this;
        }

        public Buider setEmail(String email) {
            this.email = email;
            return this;
        }

        public Buider setApproveEmail(ZonedDateTime approveEmail) {
            this.approveEmail = approveEmail;
            return this;
        }

        public Buider setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Buider setApprovePhone(ZonedDateTime approvePhone) {
            this.approvePhone = approvePhone;
            return this;
        }

        public Buider setCreateDate(ZonedDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Buider setPremiumStart(ZonedDateTime premiumStart) {
            this.premiumStart = premiumStart;
            return this;
        }

        public Buider setPremiumFinish(ZonedDateTime premiumFinish) {
            this.premiumFinish = premiumFinish;
            return this;
        }

        public Buider setAbout(String about) {
            this.about = about;
            return this;
        }

        public Buider setInterests(List<String> interests) {
            this.interests = interests;
            return this;
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
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

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
