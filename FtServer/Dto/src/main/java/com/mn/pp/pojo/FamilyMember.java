package com.mn.pp.pojo;


import com.mn.pp.BasicVo;

public class FamilyMember extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String userid;
    private String gid;
    private String showName;
    private String headurl;
    private Integer gendar;
    private Byte maritalstatus;
    private String politicalstatus;
    private java.sql.Date birthday;
    private String profession;
    private String father;
    private String mother;
    private String phonenumber;
    private String spouse;
    private String homeaddress;
    private String deeds;
    private int level;


    public String getUid() {
        return uid;
   }

    public void setUid(String uid) {
        this.uid = uid;
   }

    public String getUserid() {
        return userid;
   }

    public void setUserid(String userid) {
        this.userid = userid;
   }

    public String getGid() {
        return gid;
   }

    public void setGid(String gid) {
        this.gid = gid;
   }

    public String getShowName() {
        return showName;
   }

    public void setShowName(String showName) {
        this.showName = showName;
   }

    public String getHeadurl() {
        return headurl;
   }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
   }

    public Integer getGendar() {
        return gendar;
   }

    public void setGendar(Integer gendar) {
        this.gendar = gendar;
   }

    public Byte getMaritalstatus() {
        return maritalstatus;
   }

    public void setMaritalstatus(Byte maritalstatus) {
        this.maritalstatus = maritalstatus;
   }

    public String getPoliticalstatus() {
        return politicalstatus;
   }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
   }

    public java.sql.Date getBirthday() {
        return birthday;
   }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
   }

    public String getProfession() {
        return profession;
   }

    public void setProfession(String profession) {
        this.profession = profession;
   }

    public String getFather() {
        return father;
   }

    public void setFather(String father) {
        this.father = father;
   }

    public String getMother() {
        return mother;
   }

    public void setMother(String mother) {
        this.mother = mother;
   }

    public String getPhonenumber() {
        return phonenumber;
   }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
   }

    public String getSpouse() {
        return spouse;
   }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
   }

    public String getHomeaddress() {
        return homeaddress;
   }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
   }

    public String getDeeds() {
        return deeds;
   }

    public void setDeeds(String deeds) {
        this.deeds = deeds;
   }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

/*List columns as follows:
"uid", "userid", "gid", "showName", "headurl", "gendar", "maritalstatus",
"politicalstatus", "birthday", "profession", "father", "mother", "phonenumber", "spouse", 
"homeaddress", "deeds", "createon", "isdelete"
*/