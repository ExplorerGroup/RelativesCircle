package com.mn.pp.pojo;

import com.mn.pp.BasicVo;

public class User extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String username;
    private String uid;
    private String usertype;
    private String nickname = "暂无昵称";
    private String headurl ="";
    private Integer age = 18;
    private Byte gender =1;
    private String personalizedsignature="无";
    private String location ="无";
    private String phoneNumber ="无";


    public String getUsername() {
        return username;
   }

    public void setUsername(String username) {
        this.username = username;
   }

    public String getUid() {
        return uid;
   }

    public void setUid(String uid) {
        this.uid = uid;
   }

    public String getUsertype() {
        return usertype;
   }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
   }

    public String getNickname() {
        return nickname;
   }

    public void setNickname(String nickname) {
        this.nickname = nickname;
   }

    public String getHeadurl() {
        return headurl;
   }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
   }

    public Integer getAge() {
        return age;
   }

    public void setAge(Integer age) {
        this.age = age;
   }

    public Byte getGender() {
        return gender;
   }

    public void setGender(Byte gender) {
        this.gender = gender;
   }

    public String getPersonalizedsignature() {
        return personalizedsignature;
   }

    public void setPersonalizedsignature(String personalizedsignature) {
        this.personalizedsignature = personalizedsignature;
   }

    public String getLocation() {
        return location;
   }

    public void setLocation(String location) {
        this.location = location;
   }

    public String getPhoneNumber() {
        return phoneNumber;
   }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
   }

}

/*List columns as follows:
"username", "uid", "usertype", "createon", "updateon", "isdelete", "nickname",
"headurl", "age", "gender", "personalizedsignature", "location", "phoneNumber"
*/