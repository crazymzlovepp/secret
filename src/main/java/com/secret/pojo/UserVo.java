package com.secret.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {
    private String userId;

    private String userName;

    private String password;

    private String eMail;

    private String phone;

    private String birthPlace;

    private String hobby;

    private String remarks;

    private Date registerDate;

    private String registerIp;

    private String registerCountry;

    private String registerProvince;

    private String registerCity;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace == null ? null : birthPlace.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    public String getRegisterCountry() {
        return registerCountry;
    }

    public void setRegisterCountry(String registerCountry) {
        this.registerCountry = registerCountry == null ? null : registerCountry.trim();
    }

    public String getRegisterProvince() {
        return registerProvince;
    }

    public void setRegisterProvince(String registerProvince) {
        this.registerProvince = registerProvince == null ? null : registerProvince.trim();
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity == null ? null : registerCity.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", eMail=").append(eMail);
        sb.append(", phone=").append(phone);
        sb.append(", birthPlace=").append(birthPlace);
        sb.append(", hobby=").append(hobby);
        sb.append(", remarks=").append(remarks);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", registerIp=").append(registerIp);
        sb.append(", registerCountry=").append(registerCountry);
        sb.append(", registerProvince=").append(registerProvince);
        sb.append(", registerCity=").append(registerCity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}