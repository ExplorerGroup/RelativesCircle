package com.mn.pp.common.app;

import com.mn.pp.core.utils.JackSonUtil;
import com.mn.pp.pojo.User;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class UserDetailRetBean {

    private String nickname;
    private String headurl;
    private Integer age;
    private Byte gender;
    private String personalizedsignature;
    private String location;
    private String phonenumber;

    public UserDetailRetBean() {
    }

    public static final class Builder {
        private static Builder userDetailBeanBuilder;

        private Builder() {
        }

        public String build(int retCode, User user) {
            UserDetailRetBean userDetailRetBean = new UserDetailRetBean();
            RetTemplate retTemplate = new RetTemplate();
            if (user != null) {
                userDetailRetBean.nickname = user.getNickname();
                userDetailRetBean.headurl = user.getHeadurl();
                userDetailRetBean.age = user.getAge();
                userDetailRetBean.gender = user.getGender();
                userDetailRetBean.personalizedsignature = user.getPersonalizedsignature();
                userDetailRetBean.location = user.getLocation();
                userDetailRetBean.phonenumber = user.getPhoneNumber();
            }
            retTemplate.setRetCode(retCode);
            retTemplate.setRetValue(userDetailRetBean);
            return JackSonUtil.obj2Json(retTemplate);
        }

        public static Builder getInsBuilder() {
            if (userDetailBeanBuilder == null) {
                synchronized (Builder.class) {
                    if (userDetailBeanBuilder == null) {
                        userDetailBeanBuilder = new Builder();
                    }
                }
            }
            return userDetailBeanBuilder;
        }
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
