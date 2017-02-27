package com.android.relativescircle.model;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by fantao on 17-2-17.
 */

public class Member implements Comparable{
    private long id;
    private String name;
    private int sex;//0为男,１为女
    private int maritalStatus;
    private int politicalStatus;
    private String address;
    private int phoneNumber;
    private String profession;
    private Date dateOfBirth;
    private Date dateOfDeath;
    private String characterDeeds;
    private Drawable photo;
    private int generation;
    private Member relativeMember;
    private int sortCode = -1;
    private int weight = 1;
    private int position;

    public Member() {
    }

    public Member(long id, String name, int sex, int generation, Member relativeMember, int sortCode) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.generation = generation;
        this.relativeMember = relativeMember;
        this.sortCode = sortCode;
    }

    public Member(long id, String name, int sex, int maritalStatus, int politicalStatus, String address,
                  int phoneNumber, String profession, Date dateOfBirth, Date dateOfDeath,
                  String characterDeeds, Drawable photo, int generation, Member relativeMember, int sortCode) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.politicalStatus = politicalStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.characterDeeds = characterDeeds;
        this.photo = photo;
        this.generation = generation;
        this.relativeMember = relativeMember;
        this.sortCode = sortCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(int politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getCharacterDeeds() {
        return characterDeeds;
    }

    public void setCharacterDeeds(String characterDeeds) {
        this.characterDeeds = characterDeeds;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public long getRelativeId() {
        return relativeMember != null ? relativeMember.getId() : -1;
    }

    public Member getRelativeMember() {
        return relativeMember;
    }

    public void setRelativeMember(Member relativeMember) {
        this.relativeMember = relativeMember;
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isWife() {
        return relativeMember != null && relativeMember.getGeneration() == generation;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", maritalStatus=" + maritalStatus +
                ", politicalStatus=" + politicalStatus +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", profession='" + profession + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfDeath=" + dateOfDeath +
                ", characterDeeds='" + characterDeeds + '\'' +
                ", photo=" + photo +
                ", generation=" + generation +
                ", sortCode=" + sortCode +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object another) {
        Member am = (Member) another;
        int g = generation - am.getGeneration();
        if (g > 0) {
            return 1;
        } else if (g < 0) {
            return -1;
        }
        if (generation == 0) {
            return sortCode - am.sortCode;
        }
        Member m1 = generation == getRelativeMember().getGeneration() ? getRelativeMember() : this;
        Member m2 = am.getGeneration() == am.getRelativeMember().getGeneration() ? am.getRelativeMember() : am;
        int rc = m1.getRelativeMember().getSortCode() - m2.getRelativeMember().getSortCode();
        if (rc > 0) {
            return 1;
        } else if (rc < 0) {
            return -1;
        } else {
            if (sortCode > ((Member) another).getSortCode()) {
                return 1;
            } else if (sortCode < ((Member) another).getSortCode()) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return id == member.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
