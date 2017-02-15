package com.android.relativescircle.dto;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class UserDetail {
    public static final String USER_TYPE_DEFAULT = "PPChat_default";

    private String sessionId;
    private String nickName;
    private String headUrl;
    private int age = 20;
    private byte gender = 0;
    private String mail;
    private String personalizedSignature;
    private String location;

    public UserDetail(String sessionId, String nickName, String headUrl, int age,
                      byte gender, String mail, String personalizedSignature, String location) {
        this.sessionId = sessionId;
        this.nickName = nickName;
        this.headUrl = headUrl;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.personalizedSignature = personalizedSignature;
        this.location = location;
    }

    public UserDetail() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
