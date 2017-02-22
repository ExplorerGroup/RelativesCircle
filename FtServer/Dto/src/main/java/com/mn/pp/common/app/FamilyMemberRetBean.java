package com.mn.pp.common.app;

import com.mn.pp.pojo.FamilyMember;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public final class FamilyMemberRetBean {

    private String uid;
    private String userid;
    private String gid;
    private String name;
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

    public FamilyMemberRetBean() {
    }

    public FamilyMemberRetBean(FamilyMember familyMember){
          uid = familyMember.getUid();
          userid = familyMember.getUserid();
          gid = familyMember.getGid();
          name = familyMember.getShowName();
          headurl =familyMember.getHeadurl();
          gendar =familyMember.getGendar();
          politicalstatus = familyMember.getPoliticalstatus();
          birthday = familyMember.getBirthday();
          profession = familyMember.getProfession();
          father = familyMember.getFather();
          mother = familyMember.getMother();
          phonenumber = familyMember.getPhonenumber();
          spouse = familyMember.getSpouse();
          homeaddress = familyMember.getHomeaddress();
          deeds = familyMember.getDeeds();
          level = familyMember.getLevel();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public static final class Builder{
        private static Builder builder;

        private Builder(){}

        public static Builder getInsBuilder(){
            if(builder==null){
                synchronized (Builder.class){
                    if(builder==null){
                        builder = new Builder();
                    }
                }
            }
            return builder;
        }

        public FamilyMemberRetBean build(FamilyMember familyMember){
            return new FamilyMemberRetBean(familyMember);
        }

        public List<FamilyMemberRetBean> build(List<FamilyMember> familyMemberList){
            if(familyMemberList == null||familyMemberList.size()==0){
                return null;
            }
            List<FamilyMemberRetBean> familyMemberRetBeanList = new ArrayList<>();
            for (FamilyMember familyMember:familyMemberList){
                familyMemberRetBeanList.add(new FamilyMemberRetBean(familyMember));
            }
            return familyMemberRetBeanList;
        }
    }

}
