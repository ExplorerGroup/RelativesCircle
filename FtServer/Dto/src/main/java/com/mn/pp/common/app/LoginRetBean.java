package com.mn.pp.common.app;

import com.mn.pp.core.utils.JackSonUtil;
import com.mn.pp.pojo.LoginSession;
import com.mn.pp.pojo.User;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 登录返回的信息
 */
public final class LoginRetBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sessionId;
    private String nickName;
    private String headUrl;
    private int age = 20;
    private byte gender = 0;
    private String personalizedSignature;

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

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    @Override
    public String toString() {
        return "LoginRetBean{" +
                "sessionId='" + sessionId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", personalizedSignature='" + personalizedSignature + '\'' +
                '}';
    }

    /**
     * 构建返回json
     */
    public static final class Builder {
        private static Builder loginRetBuilder;

        private Builder() {
        }

        public String build(int retCode, LoginSession loginSession, User user) {
            LoginRetBean loginRet = new LoginRetBean();
            RetTemplate retTemplate = new RetTemplate();
            if (loginSession != null) {
                loginRet.sessionId = loginSession.getSessionid();
            }
            if (user != null) {
                loginRet.age = user.getAge();
                loginRet.gender = user.getGender();
                loginRet.headUrl = user.getHeadurl();
                loginRet.nickName = user.getNickname();
                loginRet.personalizedSignature = user.getPersonalizedsignature();
            }
            retTemplate.setRetCode(retCode);
            retTemplate.setRetValue(loginRet);
            return JackSonUtil.obj2Json(retTemplate);
        }

        public static Builder getInsBuilder() {
            if (loginRetBuilder == null) {
                synchronized (Builder.class) {
                    if (loginRetBuilder == null) {
                        loginRetBuilder = new Builder();
                    }
                }
            }
            return loginRetBuilder;
        }
    }
}
